package com.example.everytime.controller;

import com.example.everytime.DTO.PrPost.PrCmtResponseDto;
import com.example.everytime.DTO.PrPost.PrCommentDto;
import com.example.everytime.constant.Response;
import com.example.everytime.entity.PrComment;
import com.example.everytime.entity.PrPost;
import com.example.everytime.entity.User;
import com.example.everytime.service.PrCommentService;
import com.example.everytime.service.PrPostService;
import com.example.everytime.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/pr-post")
@RequiredArgsConstructor
public class PrCommentController {

    private final UserService userService;
    private final PrPostService prPostService;
    private final PrCommentService prCommentService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/comment")
    public Response<PrCmtResponseDto> prCommentSaved(@RequestBody PrCommentDto prCommentDto, Principal principal) {
        String userId = principal.getName();
        User loginUser = userService.getUserByUserId(userId);
        PrPost prPost = prPostService.getPrPost(prCommentDto.getPrId());
        PrComment prComment = PrComment.createPrComment(prCommentDto, loginUser, prPost);
        PrComment savedPrComment = prCommentService.prCommentSaved(prComment);

        return Response.success(new PrCmtResponseDto(savedPrComment.getPrCmd(), prPost.getPrId()));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @GetMapping("/{post_id}/comment/{page}")
    public Page<PrComment> prCommentList(@PathVariable int post_id, @PathVariable int page) {
        PrPost prPost = prPostService.getPrPost(post_id);
        return prCommentService.prCommentPageList(prPost, page);
    }
}
