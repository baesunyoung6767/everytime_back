package com.example.everytime.controller;

import com.example.everytime.DTO.FreePostDto;
import com.example.everytime.entity.FreePost;
import com.example.everytime.service.FreePostService;
import com.example.everytime.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/free_post")
@RestController
@RequiredArgsConstructor
public class FreePostController {
    private final FreePostService freePostService;
    private final UserService userService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/")
    public void freePostSaved(@RequestBody FreePostDto freePostDto) {
        FreePost freePost = FreePost.createFreePost(freePostDto, userService.getUserByUserId(freePostDto.getFreeUser()));
        FreePost savedFreePost = freePostService.saveFreePost(freePost);
    }
}
