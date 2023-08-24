package com.example.everytime.service;

import com.example.everytime.entity.PrPost;
import com.example.everytime.repository.PrPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PrPostService {
    private final PrPostRepository prPostRepository;

    @Transactional
    public PrPost savedPrPost(PrPost prPost) {
        return prPostRepository.save(prPost);
    }

    public Page<PrPost> getPageList(int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return prPostRepository.findAll(pageable);
    }
}
