package com.sapiofan.surveys.services.impl;

import com.sapiofan.surveys.entities.User;
import com.sapiofan.surveys.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl {
    @Autowired
    UserRepository userRepository;

    @Transactional
    public User findUserByNickname(String nickname){
        return userRepository.findByNickname(nickname);
    }
}
