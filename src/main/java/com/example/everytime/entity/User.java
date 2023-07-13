package com.example.everytime.entity;

import com.example.everytime.DTO.UserDto;
import com.example.everytime.constant.Role;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Getter @Setter
@Entity
@Table(name = "user")
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

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

    public static User createUser(UserDto userDto, PasswordEncoder passwordEncoder) {
        User user = new User();
        user.setUserId(userDto.getUserId());
        user.setUserEmail(userDto.getUserEmail());
        user.setUserUniv(userDto.getUserUniv());
        String password = passwordEncoder.encode(userDto.getUserPwd());
        user.setUserPwd(password);
        user.setRole(Role.USER);
        return user;
    }
}
