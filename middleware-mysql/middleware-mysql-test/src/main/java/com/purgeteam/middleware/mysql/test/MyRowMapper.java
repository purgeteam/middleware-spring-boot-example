package com.purgeteam.middleware.mysql.test;

import com.purgeteam.middleware.common.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author purgeyao
 * @since 1.0
 */
public class MyRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        // 获取结果集中的数据
        Integer id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        Integer age = resultSet.getInt("age");
        // 把数据封装成User对象
        User user = new User(id,name,age);
        return user;
    }
}
