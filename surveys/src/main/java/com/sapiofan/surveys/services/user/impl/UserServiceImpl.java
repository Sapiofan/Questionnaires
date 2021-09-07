package com.sapiofan.surveys.services.user.impl;

import com.sapiofan.surveys.entities.user.User;
import com.sapiofan.surveys.repository.user.UserRepository;
import com.sapiofan.surveys.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

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
    public User save(String nickname, String password) {
        User user = new User();
        user.setPassword(password);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());

        user.setNickname(nickname);
        user.setPassword(encodedPassword);
        Timestamp ts = Timestamp.from(Instant.now());
        user.setCreated_at(ts);
        userRepository.save(user);
        return user;
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
