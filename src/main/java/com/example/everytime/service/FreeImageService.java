package com.example.everytime.service;

import com.example.everytime.constant.FileHandler;
import com.example.everytime.entity.FreeImage;
import com.example.everytime.entity.FreePost;
import com.example.everytime.repository.FreeImageRepository;
import com.example.everytime.repository.FreePostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class FreeImageService {
    private final FreeImageRepository freeImageRepository;
    private final FreePostRepository freePostRepository;
    private final FileHandler fileHandler;

    @Transactional
    public FreeImage savedFreeImage(FreePost freePost, List<MultipartFile> files) throws Exception {
        List<FreeImage> imageList = fileHandler.parseFileInfo(freePost, files);

        if(imageList.isEmpty()) {
            // 에러 처리 추가
        }
        else {
            List<FreeImage> pictureBeans = new ArrayList<>();
            for(FreeImage freeImage1 : imageList) {
                pictureBeans.add(freeImageRepository.save(freeImage1));
            }
        }
        return imageList.get(0);
    }

    public List<FreeImage> findImageList() {
        return freeImageRepository.findAll();
    }

    public FreeImage findImage(int id) {
        return freeImageRepository.findById(id);
    }

    public FreeImage findImageToFreeId(int freeId) {
        FreePost freePost = freePostRepository.findByFreeId(freeId);
        return freeImageRepository.findByFreePost(freePost);
    }
}