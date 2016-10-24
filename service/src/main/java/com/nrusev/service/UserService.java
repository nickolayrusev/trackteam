package com.nrusev.service;

import com.nrusev.domain.User;
import com.nrusev.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Nikolay Rusev on 24.10.2016 г..
 */
@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findOne(Long id){
        return this.userRepository.findOne(id);
    }

    public User findByUserName(String userName){
        return this.userRepository.findByUserName(userName);
    }
}