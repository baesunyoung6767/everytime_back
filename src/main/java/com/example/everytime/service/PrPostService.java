package com.example.everytime.service;

import com.example.everytime.DTO.PrPost.PrUpdateRequestDto;
import com.example.everytime.DTO.PrPost.PrUpdateResponseDto;
import com.example.everytime.entity.PrPost;
import com.example.everytime.exception.AppException;
import com.example.everytime.exception.ErrorCode;
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

    @Transactional
    public PrPost updatePrPost(int updateId, PrUpdateRequestDto prUpdateRequestDto) {
        PrPost foundPost = prPostRepository.getReferenceById(updateId);
        PrPost updatedPost = PrPost.updatePrPost(foundPost, prUpdateRequestDto);
        return prPostRepository.save(updatedPost);
    }

    @Transactional
    public void deletePrPost(int deleteId) {
        prPostRepository.deleteById(deleteId);
    }

    public PrPost getPrPost(int postId) {
        PrPost foundPost = prPostRepository.findByPrId(postId);
        if(foundPost == null) throw new AppException(ErrorCode.PR_NOT_FOUND);
        return foundPost;
    }

    public Page<PrPost> searchPageList(String searchKeyword, int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return prPostRepository.findByPrTitleContaining(pageable, searchKeyword);
    }
}
