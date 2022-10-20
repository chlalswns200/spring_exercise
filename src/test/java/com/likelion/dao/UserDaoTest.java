package com.likelion.dao;

import com.likelion.domain.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = UserDaoFactory.class)
class UserDaoTest {

    @Autowired
    ApplicationContext context;

    @Test
    public void testName() throws SQLException, ClassNotFoundException {
        UserDao userDao = context.getBean("awsUserDao",UserDao.class);

        User user = new User("50", "Eternity", "1234");
        userDao.add(user);

        User byId = userDao.findById("50");
        assertEquals("Eternity",byId.getName());
        assertEquals("1234",byId.getPassword());
    }

}