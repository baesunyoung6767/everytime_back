package com.example.everytime.service;

import com.example.everytime.DTO.FreePost.UpdateFreeDto;
import com.example.everytime.entity.FreePost;
import com.example.everytime.exception.AppException;
import com.example.everytime.exception.ErrorCode;
import com.example.everytime.repository.FreePostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class FreePostService {
    private final FreePostRepository freePostRepository;

    @Transactional
    public FreePost saveFreePost(FreePost freePost) {
        return freePostRepository.save(freePost);
    }

    @Transactional
    public FreePost updateFreePost(int updateId, UpdateFreeDto updateFreeDto) {
        FreePost freePost = freePostRepository.findByFreeId(updateId);
        FreePost updatePost = FreePost.updateFreePost(freePost, updateFreeDto);
        return freePostRepository.save(updatePost);
    }

    @Transactional
    public void deleteFreePost(int freeId) {
        freePostRepository.deleteById(freeId);
    }

    public FreePost getPostByFreeId(int freeId) {
        FreePost freePost = freePostRepository.findByFreeId(freeId);
        if(freePost == null) {
            throw new AppException(ErrorCode.USER_NOT_FOUND); // 아이디를 찾을 수 없다
        }
        return freePost;
    }

    // 페이징 추가
    public Page<FreePost> getPageList(int page) {
        Pageable pageable = PageRequest.of(page, 10); // 조회할 페이지 번호(0부터 시작), 한 페이지에 보여줄 최대 개수
        return this.freePostRepository.findAll(pageable);
    }

    // 페이지 없이 전체 게시글 리스트 조회
    public List<FreePost> getFreePostList() {
        return freePostRepository.findAll();
    }

    public List<FreePost> getFreePostTitle(String freeTitle) {
        return freePostRepository.findByFreeTitleContaining(freeTitle);
    }
}
