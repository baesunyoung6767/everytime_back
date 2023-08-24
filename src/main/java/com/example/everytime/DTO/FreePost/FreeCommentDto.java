package com.example.everytime.DTO.FreePost;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class FreeCommentDto {
    private int freeId; // 댓글을 남긴 자유 게시글 고유 번호
    private String freeCmdUser; // 댓글 남긴 유저 아이디
    private String freeCmdContent;
}
