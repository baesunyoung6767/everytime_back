package com.example.everytime.controller;

import com.example.everytime.DTO.PrPost.*;
import com.example.everytime.constant.Response;
import com.example.everytime.entity.PrPost;
import com.example.everytime.service.PrPostService;
import com.example.everytime.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
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

    @ResponseStatus(HttpStatus.CREATED)
    @PatchMapping("/{post_id}")
    public Response<PrUpdateResponseDto> prPostUpdate(@PathVariable int post_id, @RequestBody PrUpdateRequestDto prUpdateRequestDto) {
        PrPost prPost = prPostService.updatePrPost(post_id, prUpdateRequestDto);
        return Response.success(new PrUpdateResponseDto(prPost.getPrId(), prPost.getPrTitle(), prPost.getPrContent()));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @DeleteMapping("/{post_id}")
    public Response<PrDelResponseDto> prPostDelete(@PathVariable int post_id) {
        prPostService.deletePrPost(post_id);
        return Response.success(new PrDelResponseDto(post_id));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @GetMapping("/detail/{post_id}")
    public Response<PrDetailResponseDto> prPostDetail(@PathVariable int post_id) {
        PrPost foundPost = prPostService.getPrPost(post_id);
        PrDetailResponseDto createDetailDto = new PrDetailResponseDto(foundPost.getPrId(), foundPost.getPrTitle(), foundPost.getPrContent(), foundPost.getPrDate(), foundPost.getPrUser().getUserId());
        return Response.success(createDetailDto);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @GetMapping("/search/{search_keyword}/{page}")
    public Page<PrPost> prPostSearch(@PathVariable String search_keyword, @PathVariable int page) throws UnsupportedEncodingException {
        String decodedParam = URLDecoder.decode(search_keyword, "UTF-8");
        return prPostService.searchPageList(search_keyword, page);
    }
}
