package com.purgeteam.middleware.rabbitmq.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * mq 生产
 *
 * @author purgeyao
 * @since 1.0
 */
@Slf4j
@Service
public class RabbitmqProducerService {

    @Resource
    private RabbitTemplate rabbitTemplate;

    RabbitTemplate.ConfirmCallback confirmCallback = (correlationData, isAck, cause) -> {
        log.info(correlationData.toString());
        log.info("ack: " + isAck);
        if (!isAck) {
            log.error(cause);
        }
    };

    RabbitTemplate.ReturnCallback returnCallback = (message, replyCode, replyText, exchange, routingKey) -> {
        log.info("code: " + replyCode + ", text: " + replyText);
        log.info("exchange: " + exchange + ", routingKey: " + routingKey);
        log.info(message.toString());
    };

    public void sendMsg(String msg) {
        CorrelationData cd = new CorrelationData("id" + System.currentTimeMillis());
        rabbitTemplate.setConfirmCallback(confirmCallback);
        rabbitTemplate.setReturnCallback(returnCallback);
        rabbitTemplate.convertAndSend("test-exchange", "routingkey-test", msg, cd);
    }

}
