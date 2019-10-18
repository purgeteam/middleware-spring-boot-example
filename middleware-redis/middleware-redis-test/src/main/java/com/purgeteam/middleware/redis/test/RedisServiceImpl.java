package com.purgeteam.middleware.redis.test;

import com.purgeteam.middleware.common.User;
import com.purgeteam.middleware.common.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author purgeyao
 * @since 1.0
 */
@Slf4j
@Service
public class RedisServiceImpl implements UserService<User> {

    private static final String KEY = "test-user";

    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public List<User> findUserAllList() {
        List<User> range = redisTemplate.opsForList().range(KEY, 0, -1);
        return range;
    }

    @Override
    public User addUser(User user) {
        Long aLong = redisTemplate.opsForList().rightPush(KEY, user);
        log.info(aLong.toString());
        return user;
    }

    @Override
    public Integer delUser(Integer id) {
        return null;
    }
}
