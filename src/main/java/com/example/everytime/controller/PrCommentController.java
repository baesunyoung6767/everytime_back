package com.example.everytime.controller;

import com.example.everytime.DTO.PrPost.PrCmtResponseDto;
import com.example.everytime.DTO.PrPost.PrCmtUpdateDto;
import com.example.everytime.DTO.PrPost.PrCommentDto;
import com.example.everytime.constant.Response;
import com.example.everytime.entity.PrComment;
import com.example.everytime.entity.PrPost;
import com.example.everytime.entity.User;
import com.example.everytime.service.PrCommentService;
import com.example.everytime.service.PrPostService;
import com.example.everytime.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/pr-post")
@RequiredArgsConstructor
@Slf4j
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

    @ResponseStatus(HttpStatus.CREATED)
    @PatchMapping("/comment/{comment_id}")
    public Response<Integer> prCommentUpdated(@PathVariable int comment_id, @RequestBody PrCmtUpdateDto prCmtUpdateDto, Principal principal) {
        String loginUser = principal.getName();
        PrComment updatedComment = prCommentService.prCommentUpdated(comment_id, prCmtUpdateDto, loginUser);
        log.info("홍보게시글 댓글이 성공적으로 수정되었습니다. 댓글 아이디 : " + updatedComment.getPrCmd());
        return Response.success(updatedComment.getPrCmd());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @DeleteMapping("/comment/{comment_id}")
    public Response<Integer> prCommentDelete(@PathVariable int comment_id, Principal principal) {
        String loginUser = principal.getName();
        PrComment deletedComment = prCommentService.prCommentDeleted(comment_id, loginUser);
        log.info("홍보게시글 댓글이 성공적으로 삭제되었습니다. 댓글 아이디 : " + deletedComment.getPrCmd());
        return Response.success(deletedComment.getPrCmd());
    }
}
