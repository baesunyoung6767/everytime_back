package com.example.everytime.entity;

import com.example.everytime.constant.ROLE;
import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity
public class User {
    @Id
    private String user_id;

    @Column(nullable = false, length = 50)
    private String user_pwd;

    @Column(nullable = false, length = 50)
    private String user_Univ;

    @Column(nullable = false, length = 50)
    private String user_email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ROLE role;
}
