package com.purgeteam.middleware.emqx.test.producer;

import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

/**
 * emqtt 消息发送
 *
 * @author purgeyao
 * @since 1.0
 */
@Component
@MessagingGateway(defaultRequestChannel = "mqttOutboundChannel")
public interface MqttServer {

    /**
     * 发送mqtt
     *
     * @param topic topic
     * @param data 数据
     */
    void sendToMqtt(@Header(MqttHeaders.TOPIC) String topic, String data);
}
