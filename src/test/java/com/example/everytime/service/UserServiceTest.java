package com.example.everytime.service;

import com.example.everytime.DTO.UserDto;
import com.example.everytime.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
public class UserServiceTest {
    @Autowired
    UserService userService;

    @Autowired
    BCryptPasswordEncoder encoder;

    public User createUser() {
        UserDto userDto = new UserDto();
        userDto.setUserId("bae1234");
        userDto.setUserEmail("tjsdudqo1234@naver.com");
        userDto.setUserUniv("동의대학교");
        userDto.setUserPwd("qotjsdud67");
        return User.createUser(userDto, encoder);
    }

    @Test
    @DisplayName("회원가입 테스트")
    public void saveUserTest() {
        User user = createUser();
        User savedUser = userService.saveUser(user);

        Assertions.assertEquals(user.getId(), savedUser.getId());
    }
}
