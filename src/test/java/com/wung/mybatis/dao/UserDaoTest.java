package com.wung.mybatis.dao;

import com.sun.xml.internal.ws.developer.MemberSubmissionAddressing;
import com.wung.mybatis.model.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 *
 * Created by wung on 2017/4/27.
 */
public class UserDaoTest {
    private SqlSession session;
    private UserDao userDao;

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

    /**
     * 该方法会在测试方法之前执行
     */
    @Before
    public void initSessionAndUserDaoMapper() {
        SqlSessionFactory sessionFactory = getSqlSessionFactory();
        session = sessionFactory.openSession();
        userDao = session.getMapper(UserDao.class);
        assert userDao != null;
        System.out.println("session open");
    }

    /**
     * 该方法会在测试方法之后执行
     */
    @After
    public void closeSession() {
        session.close();
        System.out.println("session close");
    }


    @Test
    public void findById() {
        User user = userDao.findById(1);
        System.out.println(user);
        assert user != null;
    }

    @Test
    public void findByIds() {
        List<Integer> ids = Arrays.asList(18, 23);
        List<User> users = userDao.findByIds(ids);
        assert (users.size() >= 0);
    }

    @Test
    public void findByIds2() {
        int[] ids = {1, 2};
        List<User> users = userDao.findByIds2(ids);
        assert (users.size() >= 0);
    }

    @Test
    public void findAllUsers() {
        List<User> users = userDao.findAllUsers();
        assert users.size() > 0;
    }



    /**
     * 修改后一定要 commit
     */
    @Test
    public void insert() {
        User user = new User();
        user.setLoginName("jack");
        user.setAge(29);

        userDao.insert(user);
        session.commit();
        assert user.getId() != null;
    }

    @Test
    public void insertBatch() {
        List<User> users = new ArrayList<>();
        User user = new User();
        user.setLoginName("Tom");
        user.setAge(20);
        users.add(user);

        User user1 = new User();
        user1.setLoginName("tony");
        user1.setAge(80);
        users.add(user1);

        int count = userDao.insertBatch(users);
        session.commit();
        assert (count == users.size());
    }


    @Test
    public void update() {
        User user = new User();
        user.setId(10);
//        user.setLoginName("mark2");
        user.setAge(30);

        int count = userDao.update(user);
        session.commit();
        assert (count >= 0);
        assert user.getId() != null;
    }

    @Test
    public void deleteById() {
        int count = userDao.deleteById(13);
        session.commit();
        assert (count >= 0);
    }

}
