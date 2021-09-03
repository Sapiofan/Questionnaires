package com.sapiofan.surveys.repository;

import com.sapiofan.surveys.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.nickname = :nickname")
    User findByNickname(String nickname);

    @Query("select u from User u where u.id=:id")
    User findUserById(Long id);
}
