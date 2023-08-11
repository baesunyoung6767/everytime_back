package com.example.everytime.service;

import com.example.everytime.entity.FreeComment;
import com.example.everytime.entity.FreePost;
import com.example.everytime.repository.FreeCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FreeCommentService {
    private final FreeCommentRepository freeCommentRepository;

    @Transactional
    public FreeComment savedFreeComment(FreeComment freeComment) {
        return freeCommentRepository.save(freeComment);
    }

    public List<FreeComment> freeCommentList() {return freeCommentRepository.findAll();}
}
