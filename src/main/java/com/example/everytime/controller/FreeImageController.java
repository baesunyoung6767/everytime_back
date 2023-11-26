package com.example.everytime.controller;

import com.example.everytime.entity.FreeImage;
import com.example.everytime.entity.FreePost;
import com.example.everytime.service.FreeImageService;
import com.example.everytime.service.FreePostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RequestMapping("/free-post")
@RestController
@RequiredArgsConstructor
@Slf4j
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

    @ResponseStatus(HttpStatus.CREATED)
    @GetMapping("/image/{free_id}")
    public ResponseEntity<Resource> getFreeImage(@PathVariable int free_id) throws IOException {

        FreeImage freeImage = freeImageService.findImageToFreeId(free_id);
        String imgPath = freeImage.getStoredFileName();
        log.info("image path : " + imgPath);

        Resource resource = new FileSystemResource("C://coding/everytime/" + imgPath);
        HttpHeaders headers = new HttpHeaders();
        Path filePath = null;

        try {
            filePath = Paths.get("C://coding/everytime/" + imgPath);
            headers.add("Content-Type", Files.probeContentType(filePath));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<Resource>(resource, headers, HttpStatus.OK);
    }
}
