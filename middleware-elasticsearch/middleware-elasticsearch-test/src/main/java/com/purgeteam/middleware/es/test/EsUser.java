package com.purgeteam.middleware.es.test;

import com.purgeteam.middleware.common.User;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * @author purgeyao
 * @since 1.0
 */
@Document(indexName = EsUser.INDEX_NAME, type = EsUser.TYPE)
public class EsUser extends User {
    public static final String INDEX_NAME = "test-es";
    public static final String TYPE = "test-es";
}
