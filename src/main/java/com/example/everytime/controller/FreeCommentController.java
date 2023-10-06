package com.example.everytime.controller;

import com.example.everytime.DTO.FreePost.FreeCmtResponseDto;
import com.example.everytime.DTO.FreePost.FreeCommentDto;
import com.example.everytime.DTO.FreePost.UpdateFreeCommentDto;
import com.example.everytime.constant.Response;
import com.example.everytime.entity.FreeComment;
import com.example.everytime.entity.FreePost;
import com.example.everytime.entity.User;
import com.example.everytime.service.FreeCommentService;
import com.example.everytime.service.FreePostService;
import com.example.everytime.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RequestMapping("/free-post")
@RestController
@RequiredArgsConstructor
@Slf4j
public class FreeCommentController {
    private final FreeCommentService freeCommentService;
    private final UserService userService;
    private final FreePostService freePostService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/comment")
    public Response<FreeCmtResponseDto> freeCommentSaved(@RequestBody FreeCommentDto freeCommentDto, Principal principal) {
        String loginUser = principal.getName();
        User user = userService.getUserByUserId(loginUser);
        FreePost freePost = freePostService.getPostByFreeId(freeCommentDto.getFreeId());
        FreeComment freeComment = FreeComment.createFreeCmd(freeCommentDto, user, freePost);

        FreeComment savedComment = freeCommentService.savedFreeComment(freeComment);

        return Response.success(new FreeCmtResponseDto(savedComment.getFreeCmd(), freePost.getFreeId()));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @GetMapping("/{post_id}/comment/{page}")
    public Page<FreeComment> freeCommentList(@PathVariable int post_id, @PathVariable int page) {
        FreePost findFreePost = freePostService.getPostByFreeId(post_id);
        return freeCommentService.freeCommentList(findFreePost, page);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @DeleteMapping("/comment/{comment_id}")
    public void deleteFreeComment( @PathVariable int comment_id) {
        freeCommentService.deletedFreeComment(comment_id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PatchMapping("/comment/{comment_id}")
    public FreeComment updateFreeComment(@PathVariable int comment_id, @RequestBody UpdateFreeCommentDto updateFreeCommentDto) {
        return freeCommentService.updatedFreeComment(comment_id, updateFreeCommentDto);
    }
}
