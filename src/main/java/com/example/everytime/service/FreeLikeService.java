package com.example.everytime.service;

import com.example.everytime.entity.FreeLike;
import com.example.everytime.entity.FreePost;
import com.example.everytime.entity.User;
import com.example.everytime.exception.AppException;
import com.example.everytime.exception.ErrorCode;
import com.example.everytime.repository.FreeLikeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FreeLikeService {
    private final FreeLikeRepository freeLikeRepository;
    private final FreePostService freePostService;

    @Transactional
    public Integer savedFreeLike(User loginUser, int postId) {
        FreePost freePost = freePostService.getPostByFreeId(postId);
        FreeLike findLike = freeLikeRepository.findByFreePostAndUserId(freePost, loginUser);
        if(findLike != null) { // 이미 db에 해당 로그인 사용자가 해당 게시글에 좋아요를 눌렀다면
           freeLikeRepository.deleteById(findLike.getLikeId());
           return 0;
        }
        else {
            FreeLike newFreeLike = FreeLike.createdFreeLike(1, loginUser, freePost);
            freeLikeRepository.save(newFreeLike);
            return 1;
        }
    }

    public Integer findFreeLike(User loginUser, int postId) {
        FreePost freePost = freePostService.getPostByFreeId(postId);
        FreeLike findLike = freeLikeRepository.findByFreePostAndUserId(freePost, loginUser);
        if(findLike == null) {
           return 0;
        }
        return 1;
    }

    public Page<FreeLike> getLikeList(User loginUser, int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return freeLikeRepository.findByUserId(loginUser, pageable);
    }
}
