package com.example.everytime.controller;

import com.example.everytime.DTO.FreePost.FreeLikeDto;
import com.example.everytime.constant.Response;
import com.example.everytime.entity.FreeLike;
import com.example.everytime.entity.User;
import com.example.everytime.service.FreeLikeService;
import com.example.everytime.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/free-post/like")
@Slf4j
public class FreeLikeController {
    private final FreeLikeService freeLikeService;
    private final UserService userService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{post_id}")
    public Response<FreeLikeDto> savedFreeLiked(Principal principal, @PathVariable int post_id) {
        String loginUser = principal.getName();
        User findUser = userService.getUserByUserId(loginUser);
        int isLiked = freeLikeService.savedFreeLike(findUser, post_id);
        log.info("좋아요 수정 요청 아이디 : " + findUser.getUserId() + ", 좋아요 수정 게시글 : " + post_id);
        return Response.success(new FreeLikeDto(isLiked, post_id));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @GetMapping("/{post_id}")
    public Response<FreeLikeDto> getDetailLike(Principal principal, @PathVariable int post_id) {
        String loginUser = principal.getName();
        User findUser = userService.getUserByUserId(loginUser);
        int isLiked = freeLikeService.findFreeLike(findUser, post_id);
        log.info("좋아요 상세 정보 요청 아이디 : " + findUser.getUserId() + ", 좋아요 조회 게시글 : " + post_id + ", 좋아요 여부 : " + isLiked);
        return Response.success(new FreeLikeDto(isLiked, post_id));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @GetMapping("/all/{page}")
    public Page<FreeLike> getAllLike(Principal principal, @PathVariable int page) {
        String loginUser = principal.getName();
        User findUser = userService.getUserByUserId(loginUser);
        log.info("좋아요 조회 요청 아이디 : " + findUser.getUserId());
        return freeLikeService.getLikeList(findUser, page);
    }
}
