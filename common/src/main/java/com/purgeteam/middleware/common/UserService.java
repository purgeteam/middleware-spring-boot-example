package com.purgeteam.middleware.common;

import java.util.List;

/**
 * @author purgeyao
 * @since 1.0
 */
public interface UserService<T> {

    /**
     * 查询全部user对象
     *
     * @return 用户集合
     */
    List<T> findUserAllList();

    /**
     * 添加user
     *
     * @param user 用户
     * @return 用户
     */
    T addUser(T user);

    /**
     * 根据 id 删除 user
     *
     * @param id 用户 id
     * @return 是否成功
     */
    Integer delUser(Integer id);

}
