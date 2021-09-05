package com.sapiofan.surveys.repository.user;

import com.sapiofan.surveys.entities.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.nickname = :nickname")
    User findByNickname(String nickname);

    @Query("select u from User u where u.id=:id")
    User findUserById(Long id);

    @Query("select u from User u")
    List<User> users();
}
