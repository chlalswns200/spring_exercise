package com.likelion.dao;

import com.likelion.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = UserDaoFactory.class)
class UserDaoTest {

    @Autowired
    ApplicationContext context;

    UserDao userDao;
    User userA;
    User userB;

    @BeforeEach
    void setUp() {
        this.userDao = context.getBean("awsUserDao", UserDao.class);
        userA = new User("1", "Eternity", "11");
        userB = new User("2", "Um", "22");
    }


    @Test
    void addAndSelect() throws SQLException {

        userDao.deleteAll();
        userDao.add(userA);

        User findUser = userDao.findById("1");
        assertEquals(userA.getName(), findUser.getName());
        assertEquals(userA.getPassword(), findUser.getPassword());
    }

    @Test
    void count() throws SQLException {
        userDao.deleteAll();

        userDao.add(userA);
        assertEquals(1, userDao.getCount());

        userDao.add(userB);
        assertEquals(2, userDao.getCount());
    }

    @Test
    void findById() throws SQLException {
        assertThrows(EmptyResultDataAccessException.class,()->{
            userDao.findById("500");
        });
    }

    @Test
    void deleteAll() throws SQLException {
        userDao.deleteAll();
        assertEquals(0, userDao.getCount());
    }

}