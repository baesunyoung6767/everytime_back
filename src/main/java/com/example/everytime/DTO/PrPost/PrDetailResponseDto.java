package com.example.everytime.DTO.PrPost;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class PrDetailResponseDto {
    private int prId;
    private String prTitle;
    private String prContent;
    private LocalDateTime prDate;
    private String prUser;
}
