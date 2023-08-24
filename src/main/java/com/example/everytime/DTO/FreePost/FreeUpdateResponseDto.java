package com.example.everytime.DTO.FreePost;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FreeUpdateResponseDto {
    int freeId;
    String freeTitle;
    String freeContent;
}
