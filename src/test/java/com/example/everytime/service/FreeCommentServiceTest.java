package com.example.everytime.service;

import com.example.everytime.DTO.FreePost.UpdateFreeCommentDto;
import com.example.everytime.entity.FreeComment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
public class FreeCommentServiceTest {
    @Autowired
    FreeCommentService freeCommentService;

    public FreeComment createFreeComment() {
        FreeComment freeComment = new FreeComment();
        freeComment.setFreeCmd(1);
        freeComment.setFreeCmdContent("댓글 작성 테스트입니다");
        freeComment.setFreeCmdDate(LocalDateTime.now());
        return freeComment;
    }

    // 1. 댓글 등록
    @Test
    @DisplayName("자유게시판 댓글 등록 테스트")
    public void saveFreePost() {
       FreeComment testFreeComment = createFreeComment();
       FreeComment savedFreeComment = freeCommentService.savedFreeComment(testFreeComment);

       Assertions.assertEquals(testFreeComment.getFreeCmdContent(), savedFreeComment.getFreeCmdContent());
    }

    // 2. 댓글 수정
    @Test
    @DisplayName("자유게시판 댓글 수정 테스트")
    public void updateFreePost() {
        FreeComment testFreeComment = createFreeComment();
        FreeComment savedFreeComment = freeCommentService.savedFreeComment(testFreeComment);

        UpdateFreeCommentDto updateFreeCommentDto = new UpdateFreeCommentDto();
        updateFreeCommentDto.setUpdateContent("댓글 수정합니다.");

        FreeComment updateFreeComment = freeCommentService.updatedFreeComment(savedFreeComment.getFreeCmd(), updateFreeCommentDto, "bae0000");

        Assertions.assertEquals(updateFreeCommentDto.getUpdateContent(), updateFreeComment.getFreeCmdContent());
    }

    // 3. 댓글 삭제
    @Test
    @DisplayName("자유게시판 댓글 삭제 테스트")
    public void deleteFreePost(){
        FreeComment testFreeComment = createFreeComment();
        FreeComment savedFreeComment = freeCommentService.savedFreeComment(testFreeComment);
        freeCommentService.deletedFreeComment(savedFreeComment.getFreeCmd(), "bae0000");
    }
}
