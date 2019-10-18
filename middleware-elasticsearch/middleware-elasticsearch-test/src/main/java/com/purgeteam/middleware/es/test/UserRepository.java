package com.purgeteam.middleware.es.test;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author purgeyao
 * @since 1.0
 */
public interface UserRepository extends ElasticsearchRepository<EsUser, Integer> {

}
