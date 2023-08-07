package com.example.everytime.service;

import com.example.everytime.DTO.UpdateFreeDto;
import com.example.everytime.entity.FreePost;
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
public class FreePostServiceTest {
    @Autowired
    FreePostService freePostService;

    public FreePost createFreePost() {
        FreePost freePost = new FreePost();
        freePost.setFreeId(1);
        freePost.setFreeTitle("안녕하세요");
        freePost.setFreeContent("첫 게시글 내용입니다");
        freePost.setFreeDate(LocalDateTime.now());
        return freePost;
    }

    @Test
    @DisplayName("자유게시판 게시글 등록 테스트")
    public void saveFreePost() {
        FreePost newFreePost = createFreePost();
        FreePost savedFreePost = freePostService.saveFreePost(newFreePost);

        Assertions.assertEquals(newFreePost.getFreeTitle(), savedFreePost.getFreeTitle());
        Assertions.assertEquals(newFreePost.getFreeContent(), savedFreePost.getFreeContent());
    }

    @Test
    @DisplayName("자유게시판 게시글 수정 테스트")
    public void updateFreePost() {
        FreePost newFreePost = createFreePost();
        FreePost savedFreePost = freePostService.saveFreePost(newFreePost);

        UpdateFreeDto updateFreeDto = new UpdateFreeDto();
        updateFreeDto.setFreeTitle("제목 수정합니다");
        updateFreeDto.setFreeContent("내용 수정합니다");

        FreePost updatedFreePost = freePostService.updateFreePost(savedFreePost.getFreeId(), updateFreeDto);

        Assertions.assertEquals(updatedFreePost.getFreeTitle(), updateFreeDto.getFreeTitle());
    }
}
