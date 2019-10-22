package com.purgeteam.middleware.emqx.test.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author purgeyao
 * @since 1.0
 */
@Data
@ConfigurationProperties("spring.mqtt")
public class MqttProperties {

    private String username;

    private String password;

    private String hostUrl;

    private String clientId;

    private String defaultTopic;

    private String completionTimeout;

    private Integer keepAlive;
}
