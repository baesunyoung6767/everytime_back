package com.example.everytime.service;

import com.example.everytime.DTO.PrPost.PrCmtUpdateDto;
import com.example.everytime.entity.PrComment;
import com.example.everytime.entity.PrPost;
import com.example.everytime.entity.User;
import com.example.everytime.exception.AppException;
import com.example.everytime.exception.ErrorCode;
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
    private final UserService userService;

    @Transactional
    public PrComment prCommentSaved(PrComment prComment) {
        return prCommentRepository.save(prComment);
    }

    public Page<PrComment> prCommentPageList(PrPost prPost, int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return this.prCommentRepository.findByPrId(prPost, pageable);
    }

    @Transactional
    public PrComment prCommentUpdated(int prCommentId, PrCmtUpdateDto prCmtUpdateDto, String loginUser) {
        PrComment findComment = prCommentRepository.findByPrCmd(prCommentId);
        checkLoginEqualCmtUser(findComment, loginUser);
        PrComment updatedComment = PrComment.updatePrComment(findComment, prCmtUpdateDto);
        return this.prCommentRepository.save(updatedComment);
    }

    @Transactional
    public PrComment prCommentDeleted(int prCommentId, String loginUser) {
        PrComment findComment = prCommentRepository.findByPrCmd(prCommentId);
        checkLoginEqualCmtUser(findComment, loginUser);
        prCommentRepository.delete(findComment);
        return findComment;
    }

    public void checkLoginEqualCmtUser(PrComment prComment, String loginUser) {
        User foundUser = userService.getUserByUserId(loginUser);
        if(!prComment.getPrUser().equals(foundUser)) {
            throw new AppException(ErrorCode.USER_NOT_MATCH);
        }
    }
}
