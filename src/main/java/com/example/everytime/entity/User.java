package com.example.everytime.entity;

import com.example.everytime.DTO.User.UpdatePwdDto;
import com.example.everytime.DTO.User.UpdateUserDto;
import com.example.everytime.DTO.User.UserDto;
import com.example.everytime.constant.Role;
import lombok.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;

@Getter @Setter
@Entity
@Table(name = "user")
@ToString
public class User {
    @Id
    @Column(nullable = false, unique = true)
    private String userId;

    @Column(nullable = false)
    private String userPwd;

    @Column(nullable = false)
    private String userUniv;

    @Column(nullable = false, unique = true)
    private String userEmail;

    @Enumerated(EnumType.STRING)
    private Role role;

    public static User createUser(UserDto userDto, BCryptPasswordEncoder encoder) {
        User user = new User();
        user.setUserId(userDto.getUserId());
        user.setUserEmail(userDto.getUserEmail());
        user.setUserUniv(userDto.getUserUniv());
        String password = encoder.encode(userDto.getUserPwd());
        user.setUserPwd(password);
        user.setRole(Role.USER);
        return user;
    }

    public static User updateUser(User user, UpdateUserDto updateUserDto) {
        user.setUserUniv(updateUserDto.getUserUniv());
        user.setUserEmail(updateUserDto.getUserEmail());
        return user;
    }

    public static User updatePwd(User user, UpdatePwdDto updatePwdDto, BCryptPasswordEncoder encoder) {
        String newPassword =  encoder.encode(updatePwdDto.getNewPassword());
        user.setUserPwd(newPassword);
        return user;
    }
}
