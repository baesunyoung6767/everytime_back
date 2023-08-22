package com.example.everytime.DTO;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;

@Getter @Setter
public class UserDto {
    private String userId;
    @Length(min = 8, message = "비밀번호는 8자 이상 입력해주세요")
    private String userPwd;
    private String userUniv;
    @Email(message = "이메일 형식으로 입력해주세요")
    private String userEmail;
}

