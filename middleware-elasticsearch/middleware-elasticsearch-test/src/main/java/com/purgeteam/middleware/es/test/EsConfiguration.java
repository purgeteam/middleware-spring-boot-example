package com.purgeteam.middleware.es.test;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

/**
 * @author purgeyao
 * @since 1.0
 */
@EnableElasticsearchRepositories(basePackages = "com.purgeteam.middleware.es.test")
@Configuration
public class EsConfiguration {
}
