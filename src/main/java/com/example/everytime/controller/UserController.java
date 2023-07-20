package com.example.everytime.controller;

import com.example.everytime.DTO.LoginRequestDto;
import com.example.everytime.DTO.LoginResponseDto;
import com.example.everytime.DTO.UserDto;
import com.example.everytime.constant.Response;
import com.example.everytime.entity.User;
import com.example.everytime.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.token.Token;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

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


}
