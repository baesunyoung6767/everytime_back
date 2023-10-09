package com.example.everytime.controller;

import com.example.everytime.entity.User;
import com.example.everytime.service.FreeLikeService;
import com.example.everytime.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/free-post")
@Slf4j
public class FreeLikeController {
    private final FreeLikeService freeLikeService;
    private final UserService userService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/like/{post_id}")
    public void savedFreeLiked(Principal principal, @PathVariable int post_id) {
        String loginUser = principal.getName();
        User findUser = userService.getUserByUserId(loginUser);
        freeLikeService.savedFreeLike(findUser, post_id);
        log.info("좋아요 수정 요청 아이디 : " + findUser.getUserId() + ", 좋아요 수정 게시글 : " + post_id);
    }
}
