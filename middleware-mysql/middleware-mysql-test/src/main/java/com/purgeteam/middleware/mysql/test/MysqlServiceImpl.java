package com.purgeteam.middleware.mysql.test;

import com.purgeteam.middleware.common.User;
import com.purgeteam.middleware.common.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author purgeyao
 * @since 1.0
 */
@Slf4j
@Service
public class MysqlServiceImpl implements UserService<User> {

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<User> findUserAllList() {
        String sql = "SELECT * FROM user";
        List<User> userList = jdbcTemplate.query(sql, new Object[]{}, new BeanPropertyRowMapper<>(User.class));
        log.info("sql查询成功");
        return userList;
    }

    @Override
    public User addUser(User user) {
        String sql = "insert into user value(?,?,?)";
        int update = jdbcTemplate.update(sql, user.getId(), user.getName(), user.getAge());
        log.info("sql插入行数{}", update);
        return user;
    }

    @Override
    public Integer delUser(Integer id) {
        return null;
    }
}
