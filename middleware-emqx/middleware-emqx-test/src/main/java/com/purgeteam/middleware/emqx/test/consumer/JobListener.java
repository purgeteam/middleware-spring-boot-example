package com.purgeteam.middleware.emqx.test.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * 触发event topic 事件
 *
 * @author purgeyao
 * @since 1.0
 */
@Slf4j
@Component
public class JobListener {

    /**
     * 监听topic
     *
     * @param mqttEvent
     */
    @EventListener(condition = "#mqttEvent.topic.equals(T(com.rtsm.zhjs.background.enums.TopicName).ROLL_CALL_DEFAULT.getValue())")
    public void onEmqttCall(MqttEvent mqttEvent) {
        log.info("接收到消息：" + mqttEvent.getMessage());
    }

    @EventListener(condition = "@ emqttPredicate.test(#mqttEvent)")
    public void onEmqttCallTest(MqttEvent mqttEvent) {
        log.info("测试通过：" + mqttEvent.getMessage());
    }
}
