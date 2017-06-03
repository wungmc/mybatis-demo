package com.wung.mybatis.dao;

import com.wung.mybatis.model.User;

import java.util.List;

/**
 *
 * Created by wung on 2017/4/27.
 */
public interface UserDao {
    User findById(int id);
    List<User> findByIds(List<Integer> ids);
    List<User> findByIds2(int[] ids);// array 用法
    List<User> findAllUsers();

    void insert(User user);
    int insertBatch(List<User> users);


    int update(User user);

    int deleteById(Integer id);
}
