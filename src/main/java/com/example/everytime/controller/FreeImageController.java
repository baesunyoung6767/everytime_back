package com.example.everytime.controller;

import com.example.everytime.entity.FreeImage;
import com.example.everytime.entity.FreePost;
import com.example.everytime.service.FreeImageService;
import com.example.everytime.service.FreePostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequestMapping("/free-post")
@RestController
@RequiredArgsConstructor
public class FreeImageController {
    private final FreeImageService freeImageService;
    private final FreePostService freePostService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{post_id}/image")
    public ResponseEntity<?> createFreeImage(@PathVariable int post_id, @Validated @RequestParam("files") List<MultipartFile> files) throws Exception {
        FreePost freePost = freePostService.getPostByFreeId(post_id);
        freeImageService.savedFreeImage(freePost, files);
        return ResponseEntity.ok().build();
    }
}
