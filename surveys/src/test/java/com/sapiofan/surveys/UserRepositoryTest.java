//package com.sapiofan.surveys;
//
//import com.sapiofan.surveys.entities.user.User;
//import com.sapiofan.surveys.security.realization.CustomUserDetailsService;
//import com.sapiofan.surveys.services.user.UserService;
//import com.sapiofan.surveys.services.user.impl.UserServiceImpl;
//import org.junit.jupiter.api.*;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
//import java.sql.Timestamp;
//import java.time.Instant;
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//public class UserRepositoryTest {
//
//    private UserService userService;
//
//    private CustomUserDetailsService customUserDetailsService;
//
//    @BeforeEach
//    void setUp() {
//        userService = new UserServiceImpl();
//        customUserDetailsService = new CustomUserDetailsService();
//    }
//
//    @Test
//    @Order(1)
//    public void testCreateUser() {
//        User user = new User();
//        user.setNickname("TestNickname");
//        user.setPassword("test2021");
//        Timestamp timestamp = Timestamp.from(Instant.now());
//        user.setCreated_at(timestamp);
//
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        String encodedPassword = passwordEncoder.encode(user.getPassword());
//        user.setPassword(encodedPassword);
//
//        userService.save(user);
//
//        User newUser = userService.findUserByNickname("TestNickname");
//
//        assertThat(user.getNickname()).isEqualTo(newUser.getNickname());
//
//    }
//
//    @Test
//    @Order(2)
//    public void testFindByNickname() {
//        String nickname = "TestNickname";
//        User user = userService.findUserByNickname(nickname);
//
//        assertThat(user.getNickname()).isEqualTo(nickname);
//    }
//
//    @Test
//    @Order(3)
//    public void createTheSameUser(){
//        if(customUserDetailsService.checkIfUserExists("TestNickname")) {
//            User user = new User();
//            user.setNickname("TestNickname");
//            user.setPassword("test2021");
//            Timestamp timestamp = Timestamp.from(Instant.now());
//            user.setCreated_at(timestamp);
//            userService.save(user);
//        }
//
//        List<User> users = userService.findAllUsers();
//        List<User> duplicates = new ArrayList<>();
//        for (User user1 : users) {
//            if(user1.getNickname().equals("TestNickname")){
//                duplicates.add(user1);
//            }
//        }
//        Assertions.assertEquals(duplicates.size(), 1);
////        userService.deleteUser(userService.findUserByNickname("TestNickname"));
//    }
//
//    @Test
//    @Order(4)
//    public void notRegisteredUser(){
//        Assertions.assertNull(userService.findUserByNickname("nicknameNickname"));
//    }
//}
//
