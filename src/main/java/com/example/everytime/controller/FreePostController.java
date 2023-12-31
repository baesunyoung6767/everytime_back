package com.example.everytime.controller;

import com.example.everytime.DTO.FreePost.*;
import com.example.everytime.constant.Response;
import com.example.everytime.entity.FreePost;
import com.example.everytime.service.FreePostService;
import com.example.everytime.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.Principal;
import java.util.List;

@Slf4j
@RequestMapping("/free-post")
@RestController
@RequiredArgsConstructor
public class FreePostController {
    private final FreePostService freePostService;
    private final UserService userService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/")
    public Response<FreeSaveResponseDto> freePostSaved(@RequestBody FreePostDto freePostDto, Principal principal) {
        String loginUser = principal.getName();
        FreePost freePost = FreePost.createFreePost(freePostDto, userService.getUserByUserId(loginUser));
        FreePost savedFreePost = freePostService.saveFreePost(freePost);
        return Response.success(new FreeSaveResponseDto(savedFreePost.getFreeId()));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PatchMapping("/{post_id}")
    public Response<FreeUpdateResponseDto> freePostUpdate(@PathVariable int post_id, @RequestBody UpdateFreeDto updateFreeDto, Principal principal) {
        String loginUser = principal.getName();
        FreePost updateFreePost = freePostService.updateFreePost(post_id, updateFreeDto,loginUser);
        return Response.success(new FreeUpdateResponseDto(updateFreePost.getFreeId(), updateFreePost.getFreeTitle(), updateFreePost.getFreeContent()));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @DeleteMapping("/{post_id}")
    public Response<FreeDelResponseDto> freePostDelete(@PathVariable int post_id, Principal principal) {
        String loginUser = principal.getName();
        freePostService.deleteFreePost(post_id, loginUser);
        return Response.success(new FreeDelResponseDto(post_id));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @GetMapping("/detail/{post_id}")
    public FreePost getFreePost(@PathVariable int post_id) {
        return freePostService.getPostByFreeId(post_id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @GetMapping("/{page}")
    public Page<FreePost> getFreePostList(@PathVariable int page) {
        Page<FreePost> paging = freePostService.getPageList(page);
        return paging;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @GetMapping("/search/{search_keyword}")
    public List<FreePost> getFreePostTitle(@PathVariable String search_keyword) throws UnsupportedEncodingException {
        String decodedParam = URLDecoder.decode(search_keyword, "UTF-8");
        return freePostService.getFreePostTitle(decodedParam);
    }
}
