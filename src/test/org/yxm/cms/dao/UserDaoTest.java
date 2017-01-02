package org.yxm.cms.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.yxm.cms.model.User;

/**
 * Created by yxm on 2016.12.28.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml", "classpath:spring-hibernate.xml"})
public class UserDaoTest {

    @Autowired
    private UserDao userDao;

    @Transactional
    @Test
    public void add() {
        User user = new User();
        userDao.add(user);
    }
}
