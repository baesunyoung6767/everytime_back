package com.example.everytime.controller;

import com.example.everytime.DTO.PrPost.PrPostDto;
import com.example.everytime.DTO.PrPost.PrSaveResponseDto;
import com.example.everytime.constant.Response;
import com.example.everytime.entity.PrPost;
import com.example.everytime.service.PrPostService;
import com.example.everytime.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/pr_post")
@RequiredArgsConstructor
public class PrPostController {
    private final PrPostService prPostService;
    private final UserService userService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/")
    public Response<PrSaveResponseDto> prPostSaved(@RequestBody PrPostDto prPostDto, Principal principal) {
        String loginUser = principal.getName();
        PrPost post = PrPost.createPrPost(prPostDto, userService.getUserByUserId(loginUser));
        PrPost savePost = prPostService.savedPrPost(post);
        return Response.success(new PrSaveResponseDto(savePost.getPrId()));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @GetMapping("/{page}")
    public Page<PrPost> prPostList(@PathVariable int page) {
        return prPostService.getPageList(page);
    }
}
