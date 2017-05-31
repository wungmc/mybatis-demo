package com.wung.mybatis.dao;

import com.wung.mybatis.model.User;

import java.util.List;

/**
 *
 * Created by wung on 2017/4/27.
 */
public interface UserDao {
    User findById(int id);

    List<User> findAllUsers();

    void insert(User user);

    void update(User user);
}
