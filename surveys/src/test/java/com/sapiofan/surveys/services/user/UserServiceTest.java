package com.sapiofan.surveys.services.user;

import com.sapiofan.surveys.entities.user.User;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    @Order(1)
    public void testCreateUser() {

        userService.save("TestNickname", "test2021");

        User newUser = userService.findUserByNickname("TestNickname");

        assertThat(newUser.getNickname()).isEqualTo("TestNickname");
    }

    @Test
    @Order(2)
    public void testFindByNickname() {
        String nickname = "TestNickname";
        User user = userService.findUserByNickname(nickname);

        assertThat(user.getNickname()).isEqualTo(nickname);
    }

    @Test
    @Order(3)
    public void testDeleteUserByNickname(){
        userService.deleteUser(userService.findUserByNickname("TestNickname"));
        Assertions.assertNull(userService.findUserByNickname("TestNickname"));
    }

    @Test
    @Order(4)
    public void notRegisteredUser(){
        Assertions.assertNull(userService.findUserByNickname("nicknameNickname"));
    }
}

