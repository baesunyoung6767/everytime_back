package com.example.everytime.controller;

import com.example.everytime.DTO.FreePostDto;
import com.example.everytime.DTO.UpdateFreeDto;
import com.example.everytime.entity.FreePost;
import com.example.everytime.service.FreePostService;
import com.example.everytime.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @ResponseStatus(HttpStatus.CREATED)
    @PatchMapping("/{post_id}")
    public void freePostUpdate(@PathVariable int post_id, @RequestBody UpdateFreeDto updateFreeDto) {
        FreePost updateFreePost = freePostService.updateFreePost(post_id, updateFreeDto);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @DeleteMapping("/{post_id}")
    public void freePostDelete(@PathVariable int post_id) {
        freePostService.deleteFreePost(post_id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @GetMapping("/{post_id}")
    public FreePost getFreePost(@PathVariable int post_id) {
        return freePostService.getPostByFreeId(post_id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @GetMapping("/")
    public List<FreePost> getFreePostList() {
        return freePostService.getFreePostList();
    }
}
