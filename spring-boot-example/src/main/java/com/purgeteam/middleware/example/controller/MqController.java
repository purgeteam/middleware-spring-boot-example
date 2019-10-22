package com.purgeteam.middleware.example.controller;

import com.purgeteam.middleware.emqx.test.TopicName;
import com.purgeteam.middleware.emqx.test.producer.MqttServer;
import com.purgeteam.middleware.rabbitmq.test.RabbitmqProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author purgeyao
 * @since 1.0
 */
@RestController
@RequestMapping("/mq")
public class MqController {

    @Resource
    private RabbitmqProducerService rabbitmqProducerService;

    @Autowired
    private MqttServer mqttServer;

    @GetMapping("/{type}/{msg}")
    public String test(@PathVariable(name = "type") String type, @PathVariable(name = "msg") String msg) {
        switch (type) {
            case "rabbitmq":
                // rabbitmq 调用生产者，发布消息
                rabbitmqProducerService.sendMsg(msg);
                return "SUCCESS";
            case "emqtt":
                // emqtt
                mqttServer.sendToMqtt(TopicName.ROLL_CALL_DEFAULT.getValue(), msg);
                return "SUCCESS";
            default:
                return "type 类型错误";
        }
    }
}
