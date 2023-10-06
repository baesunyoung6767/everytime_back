package com.example.everytime.service;

import com.example.everytime.DTO.FreePost.UpdateFreeDto;
import com.example.everytime.entity.FreePost;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

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

        FreePost updatedFreePost = freePostService.updateFreePost(savedFreePost.getFreeId(), updateFreeDto, "bae0000");

        Assertions.assertEquals(updatedFreePost.getFreeTitle(), updateFreeDto.getFreeTitle());
    }

    @Test
    @DisplayName("자유게시판 게시글 삭제 테스트")
    public void deleteFreePost() {
        FreePost newFreePost = createFreePost();
        FreePost savedFreePost = freePostService.saveFreePost(newFreePost);
        freePostService.deleteFreePost(savedFreePost.getFreeId(), "bae0000");
    }

    @Test
    @DisplayName("자유게시판 게시글 상세 조회 테스트")
    public void selectFreePost() {
        FreePost newFreePost = createFreePost();
        FreePost savedFreePost = freePostService.saveFreePost(newFreePost);

        FreePost findFreePost = freePostService.getPostByFreeId(savedFreePost.getFreeId());

        Assertions.assertEquals(findFreePost.getFreeTitle(), savedFreePost.getFreeTitle());
        Assertions.assertEquals(findFreePost.getFreeContent(), savedFreePost.getFreeContent());
    }

    @Test
    @DisplayName("자유게시판 게시글 제목 검색 테스트")
    public void findTitleFreePost() {
        FreePost newFreePost = createFreePost();
        FreePost savedFreePost = freePostService.saveFreePost(newFreePost);

        List<FreePost> findFreePost = freePostService.getFreePostTitle("안녕");

        Assertions.assertEquals(findFreePost.get(0).getFreeTitle(), savedFreePost.getFreeTitle());
        Assertions.assertEquals(findFreePost.get(0).getFreeContent(), savedFreePost.getFreeContent());
    }
}
