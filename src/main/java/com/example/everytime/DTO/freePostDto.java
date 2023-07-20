package com.example.everytime.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class freePostDto {
    private int freeId;
    private String freeUser;
    private String freeTitle;
    private String freeContent;
    private LocalDateTime freeDate;
}
