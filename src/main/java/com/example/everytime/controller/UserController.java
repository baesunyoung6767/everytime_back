package com.example.everytime.controller;

import com.example.everytime.DTO.*;
import com.example.everytime.constant.Response;
import com.example.everytime.entity.User;
import com.example.everytime.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RequestMapping("/user")
@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    private final BCryptPasswordEncoder encoder;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/register")
    public void userRegister(@RequestBody UserDto userDto) {
        User user = User.createUser(userDto, encoder);
        User savedUser = userService.saveUser(user);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/login")
    public Response<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) {
        String token = userService.login(loginRequestDto.getUserId(), loginRequestDto.getPassword());
        return Response.success(new LoginResponseDto(token));
    }

    @GetMapping("/info")
    public User currentUser(Principal principal) {
        String login_user = principal.getName(); // 로그인된 유저 아이디를 반환
        return userService.getUserByUserId(login_user);
    }

    @PatchMapping("/update")
    public void updateUser(@RequestBody UpdateUserDto updateUserDto, Principal principal) {
        String login_user = principal.getName();
        User updateUser = userService.updateUserInfo(login_user, updateUserDto);
    }

    @PatchMapping("/password")
    public void updatePwd(@RequestBody UpdatePwdDto updatePwdDto, Principal principal) {
        String login_user = principal.getName();
        User updatePwd = userService.updatePwd(login_user, updatePwdDto);
    }
}
