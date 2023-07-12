package com.example.everytime.DTO;

import com.example.everytime.constant.ROLE;
import com.example.everytime.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private String user_id;
    private String user_pwd;
    private String user_Univ;
    private String user_email;
    private ROLE role;

    // DTO -> Entity
    public User toEntity() {
        User user = User.builder()
                .user_id(user_id)
                .user_pwd(user_pwd)
                .user_Univ(user_Univ)
                .user_email(user_email)
                .role(role.USER)
                .build();
        return user;
    }
}
