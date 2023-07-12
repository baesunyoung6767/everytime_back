package com.example.everytime.DTO;

import com.example.everytime.constant.ROLE;
import com.example.everytime.entity.User;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class UserSessionDto implements Serializable {
    private String user_id;
    private String user_pwd;
    private String user_Univ;
    private String user_email;
    private ROLE role;

    // Entity -> Dto
    public UserSessionDto(User user) {
        this.user_id = user.getUser_id();
        this.user_pwd = user.getUser_pwd();
        this.user_Univ = user.getUser_Univ();
        this.user_email = user.getUser_email();
    }
}
