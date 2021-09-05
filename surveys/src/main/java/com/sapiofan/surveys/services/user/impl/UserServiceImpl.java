package com.sapiofan.surveys.services.user.impl;

import com.sapiofan.surveys.entities.user.User;
import com.sapiofan.surveys.repository.user.UserRepository;
import com.sapiofan.surveys.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Transactional
    public User findUserByNickname(String nickname) {
        return userRepository.findByNickname(nickname);
    }

    @Transactional
    public User findUserById(Long id) {
        return userRepository.findUserById(id);
    }

    @Override
    @Transactional
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    @Transactional
    public List<User> findAllUsers() {
        return userRepository.users();
    }

    @Override
    @Transactional
    public void deleteUser(User user) {
        userRepository.delete(user);
    }
}
