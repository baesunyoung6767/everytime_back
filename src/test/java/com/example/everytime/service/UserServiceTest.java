package com.example.everytime.service;

import com.example.everytime.DTO.User.UpdatePwdDto;
import com.example.everytime.DTO.User.UserDto;
import com.example.everytime.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    public UpdatePwdDto updatePwd() {
        UpdatePwdDto updatePwdDto = new UpdatePwdDto();
        updatePwdDto.setNewPassword("tjsdudqo1234");
        return updatePwdDto;
    }

    @Test
    @DisplayName("회원가입 테스트")
    public void saveUserTest() {
        User user = createUser();
        User savedUser = userService.saveUser(user);

        Assertions.assertEquals(user.getUserId(), savedUser.getUserId());
    }

    @Test
    @DisplayName("회원 정보 출력 테스트")
    public void selectUserTest() {
        User user = createUser();
        User savedUser = userService.saveUser(user);
        User loginUser = userService.getUserByUserId(savedUser.getUserId());

        Assertions.assertEquals(loginUser.getUserId(), savedUser.getUserId());
    }

    @Test
    @DisplayName("비밀번호 변경")
    public void changePwd() {
        User user = createUser();
        User savedUser = userService.saveUser(user);

        UpdatePwdDto updatePwdDto = updatePwd();
        User newPwdUser = userService.updatePwd(savedUser.getUserId(),updatePwdDto);

        org.assertj.core.api.Assertions.assertThat(encoder.matches(updatePwdDto.getNewPassword(), newPwdUser.getUserPwd())).isTrue();
    }
}
