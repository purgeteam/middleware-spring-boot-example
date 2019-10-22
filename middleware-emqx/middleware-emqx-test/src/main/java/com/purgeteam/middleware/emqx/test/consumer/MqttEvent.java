package com.purgeteam.middleware.emqx.test.consumer;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * 设置观察者模式接收消息（事件）
 *
 * @author purgeyao
 * @since 1.0
 */
@Getter
public class MqttEvent extends ApplicationEvent {

    /**
     * topic
     */
    private String topic;

    /**
     * 发送的消息
     */
    private String message;

    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public MqttEvent(Object source, String topic, String message) {
        super(source);
        this.topic = topic;
        this.message = message;
    }
}
