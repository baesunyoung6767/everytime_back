package com.example.everytime.service;

import com.example.everytime.DTO.UpdateFreeDto;
import com.example.everytime.entity.FreePost;
import com.example.everytime.repository.FreePostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class FreePostService {
    private final FreePostRepository freePostRepository;

    @Transactional
    public FreePost saveFreePost(FreePost freePost) {
        return freePostRepository.save(freePost);
    }

    @Transactional
    public FreePost updateFreePost(int updateId, UpdateFreeDto updateFreeDto) {
        FreePost freePost = freePostRepository.findByFreeId(updateId);
        FreePost updatePost = FreePost.updateFreePost(freePost, updateFreeDto);
        return freePostRepository.save(updatePost);
    }
}
