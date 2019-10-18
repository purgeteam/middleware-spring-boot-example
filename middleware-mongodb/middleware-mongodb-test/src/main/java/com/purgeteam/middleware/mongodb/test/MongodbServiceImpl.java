package com.purgeteam.middleware.mongodb.test;

import com.purgeteam.middleware.common.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author purgeyao
 * @since 1.0
 */
@Slf4j
public class MongodbServiceImpl implements UserService<MongoUser> {

    @Resource
    private MongoTemplate mongoTemplate;

    @Override
    public List<MongoUser> findUserAllList() {
        Query query = Query.query(Criteria.where("dataStatus").is(1));
        List<MongoUser> users = this.mongoTemplate.find(query, MongoUser.class);
        return users;
    }

    @Override
    public MongoUser addUser(MongoUser user) {
        MongoUser insert = mongoTemplate.insert(user);
        log.info("mongo添加");
        return insert;
    }

    @Override
    public Integer delUser(Integer id) {
        return null;
    }
}
