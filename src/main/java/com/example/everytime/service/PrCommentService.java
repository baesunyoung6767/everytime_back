package com.example.everytime.service;

import com.example.everytime.entity.PrComment;
import com.example.everytime.entity.PrPost;
import com.example.everytime.repository.PrCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PrCommentService {
    private final PrCommentRepository prCommentRepository;

    @Transactional
    public PrComment prCommentSaved(PrComment prComment) {
        return prCommentRepository.save(prComment);
    }

    public Page<PrComment> prCommentPageList(PrPost prPost, int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return this.prCommentRepository.findByPrId(prPost, pageable);
    }
}
