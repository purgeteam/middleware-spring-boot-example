package com.purgeteam.middleware.emqx.test.config;

import com.purgeteam.middleware.emqx.test.consumer.MqttEvent;
import com.purgeteam.middleware.emqx.test.TopicName;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;

import javax.annotation.Resource;
import java.util.Arrays;


/**
 * @author purgeyao
 * @since 1.0
 */
@Slf4j
@Configuration
@EnableConfigurationProperties(MqttProperties.class)
public class MqttConfiguration {

    @Resource
    private MqttProperties mqttProperties;

    @Bean
    public MqttConnectOptions getMqttConnectOptions() {
        MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
        mqttConnectOptions.setUserName(mqttProperties.getUsername());
        mqttConnectOptions.setPassword(mqttProperties.getPassword().toCharArray());
        mqttConnectOptions.setServerURIs(new String[]{mqttProperties.getHostUrl()});
        mqttConnectOptions.setKeepAliveInterval(2);
        mqttConnectOptions.setKeepAliveInterval(mqttProperties.getKeepAlive());
        return mqttConnectOptions;
    }

    @Bean
    public MqttPahoClientFactory mqttClientFactory() {
        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        factory.setConnectionOptions(getMqttConnectOptions());
        return factory;
    }

    /**
     * 配置 client,监听的topic
     */
    @Bean
    public MessageProducer inbound() {
        MqttPahoMessageDrivenChannelAdapter adapter =
                new MqttPahoMessageDrivenChannelAdapter(mqttProperties.getClientId() + "_inbound", mqttClientFactory(),
                        mqttProperties.getDefaultTopic().split(","));
        adapter.setCompletionTimeout(Long.valueOf(mqttProperties.getCompletionTimeout()));
        adapter.setConverter(new DefaultPahoMessageConverter());

        // 默认添加 TopicName 中所有 tipic
        Arrays.stream(TopicName.values()).forEach(topicName -> adapter.addTopic(topicName.getValue(), 2));
        adapter.addTopic();
        adapter.setQos(2);
        adapter.setOutputChannel(mqttInputChannel());
        return adapter;
    }

    /**
     * 事件触发
     */
    @Resource
    private ApplicationEventPublisher eventPublisher;

    @Bean
    @ServiceActivator(inputChannel = "mqttInputChannel")
    public MessageHandler handler() {
        return new MessageHandler() {
            @Override
            public void handleMessage(Message<?> message) throws MessagingException {
                String topic = message.getHeaders().get("mqtt_receivedTopic").toString();
                String qos = message.getHeaders().get("mqtt_receivedQos").toString();
                // 触发事件 这里不再做业务处理，包 listener 中做处理
                eventPublisher.publishEvent(new MqttEvent(this, topic, message.getPayload().toString()));
                log.info("topic:" + topic + " Qos:" + qos + " message:" + message.getPayload());
            }
        };
    }

    @Bean
    @ServiceActivator(inputChannel = "mqttOutboundChannel")
    public MessageHandler mqttOutbound() {
        MqttPahoMessageHandler messageHandler = new MqttPahoMessageHandler(mqttProperties.getClientId(), mqttClientFactory());
        messageHandler.setAsync(true);
        messageHandler.setDefaultTopic(mqttProperties.getDefaultTopic());
        return messageHandler;
    }

    @Bean
    public MessageChannel mqttInputChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageChannel mqttOutboundChannel() {
        return new DirectChannel();
    }

}
