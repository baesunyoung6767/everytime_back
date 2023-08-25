package com.example.everytime.DTO.PrPost;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class PrUpdateResponseDto {
    private int prId;
    private String title;
    private String content;
}
