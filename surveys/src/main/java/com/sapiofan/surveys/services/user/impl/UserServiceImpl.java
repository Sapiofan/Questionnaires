package com.sapiofan.surveys.services.user.impl;

import com.sapiofan.surveys.entities.user.User;
import com.sapiofan.surveys.repository.user.UserRepository;
import com.sapiofan.surveys.services.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger log = LoggerFactory.getLogger("log");

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public User findUserByNickname(String nickname) {
        log.info("find user by nickname " + nickname);
        return userRepository.findByNickname(nickname);
    }

    @Transactional
    public User findUserById(Long id) {
        log.info("find user by id " + id);
        return userRepository.findUserById(id);
    }

    @Override
    @Transactional
    public User save(String nickname, String password) {
        log.info("start of saving the user. Nickname " + nickname);
        User user = new User();
        user.setPassword(password);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());

        user.setNickname(nickname);
        user.setPassword(encodedPassword);
        Timestamp ts = Timestamp.from(Instant.now());
        user.setCreated_at(ts);
        userRepository.save(user);
        log.info("user was successfully saved");
        return user;
    }

    @Override
    @Transactional
    public List<User> findAllUsers() {
        log.info("finding of all users");
        return userRepository.users();
    }

    @Override
    @Transactional
    public void deleteUser(User user) {
        log.warn("start deleting of user nickname = " + user.getNickname());
        userRepository.delete(user);
        log.warn("user was deleted");
    }
}
