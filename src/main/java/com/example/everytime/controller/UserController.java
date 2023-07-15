package com.example.everytime.controller;

import com.example.everytime.DTO.LoginRequestDto;
import com.example.everytime.DTO.LoginResponseDto;
import com.example.everytime.DTO.UserDto;
import com.example.everytime.constant.Response;
import com.example.everytime.entity.User;
import com.example.everytime.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@RequestMapping("/user")
@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/register")
    public void userRegister(@RequestBody UserDto userDto) {
        User user = User.createUser(userDto, passwordEncoder);
        User savedUser = userService.saveUser(user);
    }

//    @ResponseStatus(HttpStatus.CREATED)
//    @PostMapping("/login")
//    public Response<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) {
//        String token = userService.login(loginRequestDto.getUserId(), loginRequestDto.getPassword());
//        return Response.success(new LoginResponseDto(token));
//    }

    // postman 설치하고 아래 코드는 삭제하고, 주석처리된 코드 다시 테스트해봐야 할 듯
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/login")
    public void login(@RequestBody LoginRequestDto loginRequestDto) {
        String token = userService.login(loginRequestDto.getUserId(), loginRequestDto.getPassword());
    }
}
