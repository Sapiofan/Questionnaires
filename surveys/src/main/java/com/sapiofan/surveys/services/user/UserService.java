package com.sapiofan.surveys.services.user;

import com.sapiofan.surveys.entities.user.User;

import java.util.List;

public interface UserService {
    User findUserByNickname(String nickname);

    User findUserById(Long id);

    void save(User user);

    List<User> findAllUsers();

    void deleteUser(User user);
}
