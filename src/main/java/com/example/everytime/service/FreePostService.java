package com.example.everytime.service;

import com.example.everytime.entity.FreePost;
import com.example.everytime.repository.FreePostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FreePostService {
    private final FreePostRepository freePostRepository;

    @Transactional
    public FreePost saveFreePost(FreePost freePost) {
        return freePostRepository.save(freePost);
    }
}
