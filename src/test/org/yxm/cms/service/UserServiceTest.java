package org.yxm.cms.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.yxm.cms.model.User;

import java.util.Date;

/**
 * Created by yxm on 2016.12.28.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml", "classpath:spring-hibernate.xml"})
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void add() {
        for (int i = 1; i <= 10; i++) {
            User user = new User();
            user.setUsername("yxm" + i);
            user.setPassword("123456");
            user.setEmail("yxm" + i + "@qq.com");
            user.setSex(true);
            user.setAdmin(false);
            user.setCreateDate(new Date());
            userService.add(user);
        }

    }
}
