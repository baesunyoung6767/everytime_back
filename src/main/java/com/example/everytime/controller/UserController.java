package com.example.everytime.controller;

import com.example.everytime.DTO.*;
import com.example.everytime.constant.Response;
import com.example.everytime.entity.User;
import com.example.everytime.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RequestMapping("/user")
@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    private final BCryptPasswordEncoder encoder;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/register")
    public String userRegister(@Valid @RequestBody UserDto userDto) {
        User user = User.createUser(userDto, encoder);
        User savedUser = userService.saveUser(user);
        return "회원가입이 성공적으로 완료되었습니다.";
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/login")
    public Response<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) {
        String token = userService.login(loginRequestDto.getUserId(), loginRequestDto.getPassword());
        return Response.success(new LoginResponseDto(token));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @GetMapping("/info")
    public Response<UserInfoResponseDto> currentUser(Principal principal) {
        String login_user = principal.getName(); // 로그인된 유저 아이디를 반환
        User userInfo =  userService.getUserByUserId(login_user);
        return Response.success(new UserInfoResponseDto(userInfo.getUserId(), userInfo.getUserUniv(), userInfo.getUserEmail()));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PatchMapping("/update")
    public Response<UserUpdateResponseDto> updateUser(@RequestBody UpdateUserDto updateUserDto, Principal principal) {
        String login_user = principal.getName();
        User updateUser = userService.updateUserInfo(login_user, updateUserDto);
        return Response.success(new UserUpdateResponseDto(updateUser.getUserUniv(), updateUser.getUserEmail()));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PatchMapping("/password")
    public String updatePwd(@RequestBody UpdatePwdDto updatePwdDto, Principal principal) {
        String login_user = principal.getName();
        User updatePwd = userService.updatePwd(login_user, updatePwdDto);
        return "비밀번호가 성공적으로 변경되었습니다.";
    }
}
