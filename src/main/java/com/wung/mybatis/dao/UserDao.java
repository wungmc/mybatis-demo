package com.wung.mybatis.dao;

import com.wung.mybatis.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 * Created by wung on 2017/4/27.
 */
public interface UserDao {
    User findById(int id);
    List<User> findByIds(List<Integer> ids); // list 用法
    List<User> findByIds2(int[] ids);// array 用法
    List<User> findByNameAndAge(String name, Integer age); //多参数，不指定参数名
    List<User> findByNameAndAge2(String name, Integer age); //多参数，不指定参数名
    List<User> findByNameAndAge3(@Param("loginName") String loginName, @Param("age") Integer age); //多参数，指定参数名
    List<User> findByNameAndAges(@Param("name") String name, @Param("ages") List<Integer> ages); //多参数，其中有复杂参数
    List<User> findAllUsers();

    void insert(User user);
    int insertBatch(List<User> users);


    int update(User user);

    int deleteById(Integer id);
}
