package com.purgeteam.middleware.example.controller;

import com.purgeteam.middleware.rabbitmq.test.RabbitmqProducerService;
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

    @GetMapping("/rabbitmq/{msg}")
    public String test(@PathVariable(name = "msg") String msg) {
        // 调用生产者，发布消息
        rabbitmqProducerService.sendMsg(msg);
        return "SUCCESS";
    }
}
