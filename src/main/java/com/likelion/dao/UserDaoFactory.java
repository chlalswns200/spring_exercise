package com.likelion.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserDaoFactory {

    @Bean
    public UserDao awsUserDao() {
        AwsConnectionMake awsConnection = new AwsConnectionMake();
        UserDao userDao = new UserDao();
        return userDao;
    }
}
