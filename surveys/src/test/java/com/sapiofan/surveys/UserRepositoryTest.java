package com.sapiofan.surveys;

import com.sapiofan.surveys.entities.user.User;
import com.sapiofan.surveys.repository.user.UserRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;

import java.sql.Timestamp;
import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

//    @Autowired
//    private UserServiceImpl userService;

    @Autowired
    private UserRepository repository;

//    @Autowired
//    private CustomUserDetailsService customUserDetailsService;

    @Test
    @Order(1)
    public void testCreateUser() {
        User user = new User();
        user.setNickname("TestNickname");
        user.setPassword("test2021");
        Timestamp timestamp = Timestamp.from(Instant.now());
        user.setCreated_at(timestamp);

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        User savedUser = repository.save(user);

        User existUser = entityManager.find(User.class, savedUser.getId());

        assertThat(user.getNickname()).isEqualTo(existUser.getNickname());

    }

    @Test
    @Order(2)
    public void testFindByNickname() {
        String nickname = "TestNickname";
        User user = repository.findByNickname(nickname);

        assertThat(user.getNickname()).isEqualTo(nickname);
    }

    @Test
    @Order(3)
    public void createTheSameUser(){
//        if(customUserDetailsService.checkIfUserExists("TestNickname")) {
//            User user = new User();
//            user.setNickname("TestNickname");
//            user.setPassword("test2021");
//            Timestamp timestamp = Timestamp.from(Instant.now());
//            user.setCreated_at(timestamp);
//            repository.save(user);
//        }

//        List<User> users = userService.findAllUsers();
//        List<User> duplicates = new ArrayList<>();
//        for (User user1 : users) {
//            if(user1.getNickname().equals("TestNickname")){
//                duplicates.add(user1);
//            }
//        }
//        Assertions.assertEquals(duplicates.size(), 1);
    }

    @Test
    @Order(4)
    public void notRegisteredUser(){
        Assertions.assertNull(repository.findByNickname("nicknameNickname"));
    }
}

