package com.purgeteam.middleware.emqx.test.consumer;

import org.springframework.stereotype.Component;

/**
 * @author purgeyao
 * @since 1.0
 */
@Component
public class EmqttPredicate {

    public Boolean test(MqttEvent event){
        //测试内容
        return Boolean.FALSE;
    }

}
