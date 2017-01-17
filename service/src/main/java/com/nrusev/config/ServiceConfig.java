package com.nrusev.config;

import com.nrusev.domain.User;
import com.nrusev.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Optional;

/**
 * Created by Nikolay Rusev on 17.1.2017 г..
 */
@Configuration
public class ServiceConfig {
    private final UserService userService;

    @Autowired
    public ServiceConfig(UserService userService) {
        this.userService = userService;
    }

    @Bean
    public User user(){
        return userService.findByUserName("nrusev").orElseThrow(IllegalStateException::new);
    }
}
