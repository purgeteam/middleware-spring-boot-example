package com.purgeteam.middleware.example.controller;

import com.purgeteam.middleware.common.User;
import com.purgeteam.middleware.es.test.EsServiceImpl;
import com.purgeteam.middleware.es.test.EsUser;
import com.purgeteam.middleware.mysql.test.MysqlServiceImpl;
import com.purgeteam.middleware.redis.test.RedisServiceImpl;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author purgeyao
 * @since 1.0
 */
@RestController
@RequestMapping("/db")
public class DbController {

    @Resource
    private MysqlServiceImpl mysqlService;

    @Resource
    private RedisServiceImpl redisService;

    @Resource
    private EsServiceImpl esService;

    @GetMapping("{type}/list")
    public Object list(@PathVariable(name = "type") String type) {
        switch (type) {
            case "mysql":
                return mysqlService.findUserAllList();
            case "redis":
                return redisService.findUserAllList();
            case "mongodb":

            case "influxdb":

            case "es":
                return esService.findUserAllList();
            default:
                return "type 类型错误";
        }
    }

    @PostMapping("{type}/add")
    public Object add(@PathVariable(name = "type") String type, @RequestBody User user) {
        switch (type) {
            case "mysql":
                return mysqlService.addUser(user);
            case "redis":
                return redisService.addUser(user);
            case "mongodb":

            case "influxdb":

            case "es":
                EsUser esUser = new EsUser();
                BeanUtils.copyProperties(user, esUser);
                return esService.addUser(esUser);
            default:
                return "type 类型错误";
        }
    }

    @Getter
    @AllArgsConstructor
    enum DbType {
        /**
         *
         */
        MYSQL("mysql"), REDIS("redis"), MONGODB("mongodb"), INFLUXDB("influxdb"), ELASTICSEARCH("es");


        private String code;

    }
}
