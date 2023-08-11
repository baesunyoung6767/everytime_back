package com.example.everytime.controller;

import com.example.everytime.DTO.FreeCommentDto;
import com.example.everytime.entity.FreeComment;
import com.example.everytime.entity.FreePost;
import com.example.everytime.entity.User;
import com.example.everytime.repository.FreeCommentRepository;
import com.example.everytime.service.FreeCommentService;
import com.example.everytime.service.FreePostService;
import com.example.everytime.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/free_post")
@RestController
@RequiredArgsConstructor
@Slf4j
public class FreeCommentController {
    private final FreeCommentService freeCommentService;
    private final UserService userService;
    private final FreePostService freePostService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/comment")
    public void freeCommentSaved(@RequestBody FreeCommentDto freeCommentDto) {
        User user = userService.getUserByUserId(freeCommentDto.getFreeCmdUser());
        FreePost freePost = freePostService.getPostByFreeId(freeCommentDto.getFreeId());
        FreeComment freeComment = FreeComment.createFreeCmd(freeCommentDto, user, freePost);

        freeCommentService.savedFreeComment(freeComment);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @GetMapping("/comment")
    public List<FreeComment> freeCommentList() {
        return freeCommentService.freeCommentList();
    }
}
