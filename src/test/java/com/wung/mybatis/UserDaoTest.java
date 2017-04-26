package com.wung.mybatis;

import com.wung.mybatis.dao.UserDao;
import com.wung.mybatis.model.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

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

    public void findUserById() {
        SqlSession session = getSqlSessionFactory().openSession();
        UserDao userDaoMapping = session.getMapper(UserDao.class);
        User user = userDaoMapping.findById(1);
        System.out.println(user);
    }

}
