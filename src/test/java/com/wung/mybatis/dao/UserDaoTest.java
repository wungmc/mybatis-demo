package com.wung.mybatis.dao;

import com.wung.mybatis.model.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 *
 * Created by wung on 2017/4/27.
 */
public class UserDaoTest {

    private static SqlSessionFactory getSqlSessionFactory() {
        SqlSessionFactory sessionFactory = null;
        String config = "mybatis-config.xml";
        try {
            sessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader(config));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sessionFactory;
    }

    @Test
    public void findById() {
        SqlSessionFactory sessionFactory = getSqlSessionFactory();
        SqlSession session = sessionFactory.openSession();

        try {
            UserDao userDaoMapping = session.getMapper(UserDao.class);
            User user = userDaoMapping.findById(1);
            System.out.println(user);
            assert user != null;
        } finally {
            session.close();
        }
    }

    @Test
    public void findByIds() {
        SqlSessionFactory sessionFactory = getSqlSessionFactory();
        SqlSession session = sessionFactory.openSession();
        try {
            UserDao userDaoMapping = session.getMapper(UserDao.class);
//            List<Integer> ids = new ArrayList(){{
//                    add(1);
//                    add(2);
//            }};
            List<Integer> ids = Arrays.asList(18, 23);
            List<User> users = userDaoMapping.findByIds(ids);
            assert (users.size() >= 0);
        } finally {
            session.close();
        }
    }

    @Test
    public void findByIds2() {
        SqlSessionFactory sessionFactory = getSqlSessionFactory();
        SqlSession session = sessionFactory.openSession();
        try {
            UserDao userDaoMapping = session.getMapper(UserDao.class);
            int[] ids = {1, 2};
            List<User> users = userDaoMapping.findByIds2(ids);
            assert (users.size() >= 0);
        } finally {
            session.close();
        }
    }

    @Test
    public void findAllUsers() {
        SqlSessionFactory sessionFactory = getSqlSessionFactory();
        SqlSession session = sessionFactory.openSession();

        try {
            UserDao userDaoMapping = session.getMapper(UserDao.class);

            List<User> users = userDaoMapping.findAllUsers();
            assert users.size() > 0;
        } finally {
            session.close();
        }
    }



    /**
     * 修改后一定要 commit
     */
    @Test
    public void insert() {
        User user = new User();
        user.setLoginName("jack");
        user.setAge(29);

        SqlSessionFactory sessionFactory = getSqlSessionFactory();
        SqlSession session = sessionFactory.openSession();
        try {
            UserDao userDaoMapping = session.getMapper(UserDao.class);
            userDaoMapping.insert(user);
            session.commit();
            assert user.getId() != null;
        } finally {
            session.close();
        }
    }

    @Test
    public void insertBatch() {
        List<User> users = new ArrayList<>();
        User user = new User();
        user.setLoginName("Tom");
        user.setAge(20);

        User user1 = new User();
        user1.setLoginName("tony");
        user1.setAge(80);

        users.add(user);
        users.add(user1);

        SqlSessionFactory sessionFactory = getSqlSessionFactory();
        SqlSession session = sessionFactory.openSession();
        try {
            UserDao userDaoMapping = session.getMapper(UserDao.class);
            int count = userDaoMapping.insertBatch(users);
            session.commit();
            assert (count == users.size());
        } finally {
            session.close();
        }
    }


    @Test
    public void update() {
        User user = new User();
        user.setId(10);
//        user.setLoginName("mark2");
        user.setAge(30);

        SqlSessionFactory sessionFactory = getSqlSessionFactory();
        SqlSession session = sessionFactory.openSession();
        try {
            UserDao userDaoMapping = session.getMapper(UserDao.class);
            int count = userDaoMapping.update(user);
            session.commit();
            assert (count >= 0);
            assert user.getId() != null;
        } finally {
            session.close();
        }
    }

    @Test
    public void deleteById() {
        SqlSessionFactory sessionFactory = getSqlSessionFactory();
        SqlSession session = sessionFactory.openSession();
        try {
            UserDao userDaoMapping = session.getMapper(UserDao.class);
            int count = userDaoMapping.deleteById(13);
            session.commit();
            assert (count >= 0);
        } finally {
            session.close();
        }
    }
}
