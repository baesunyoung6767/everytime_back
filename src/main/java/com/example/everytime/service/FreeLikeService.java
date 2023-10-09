package com.example.everytime.service;

import com.example.everytime.entity.FreeLike;
import com.example.everytime.entity.FreePost;
import com.example.everytime.entity.User;
import com.example.everytime.repository.FreeLikeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FreeLikeService {
    private final FreeLikeRepository freeLikeRepository;
    private final FreePostService freePostService;

    @Transactional
    public FreeLike savedFreeLike(User loginUser, int postId) {
        FreePost freePost = freePostService.getPostByFreeId(postId);
        FreeLike findLike = freeLikeRepository.findByFreePostAndUserId(freePost, loginUser);
        if(findLike != null) { // 이미 db에 해당 로그인 사용자가 해당 게시글에 좋아요를 눌렀다면
            int isLikedNum = findLike.getIsLiked() == 0 ? 1 : 0;
            findLike.setIsLiked(isLikedNum);
            return findLike;
        }
        else {
            FreeLike newFreeLike = FreeLike.createdFreeLike(1, loginUser, freePost);
            return freeLikeRepository.save(newFreeLike);
        }
    }
}
