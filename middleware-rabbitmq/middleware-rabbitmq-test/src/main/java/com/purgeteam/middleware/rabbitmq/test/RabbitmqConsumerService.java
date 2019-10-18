package com.purgeteam.middleware.rabbitmq.test;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

/**
 * mq 消费
 *
 * @author purgeyao
 * @since 1.0
 */
@Slf4j
@Service
public class RabbitmqConsumerService {
    @RabbitListener(
            bindings = @QueueBinding(
                    value = @Queue(value = "test-queue", durable = "true"),
                    exchange = @Exchange(value = "test-exchange", type = "topic"),
                    key = "routingkey-test"
            )
    )
    @RabbitHandler
    public void testConsumer(@Payload String msg, Channel channel, @Headers Map<String, Object> headers) {
        log.info("Rabbitmq 消费消息:{}", msg);
        Long tag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
        try {
            // false表示不批量接收，只接收当前消息
            channel.basicAck(tag, false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
