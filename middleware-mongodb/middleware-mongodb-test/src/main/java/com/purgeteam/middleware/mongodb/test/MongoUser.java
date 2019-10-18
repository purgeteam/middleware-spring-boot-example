package com.purgeteam.middleware.mongodb.test;

import com.purgeteam.middleware.common.User;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author purgeyao
 * @since 1.0
 */
@Document(collection="test-user")
@NoArgsConstructor
public class MongoUser extends User {

    public MongoUser(Integer id, String name, Integer age) {
        super(id, name, age);
    }

}
