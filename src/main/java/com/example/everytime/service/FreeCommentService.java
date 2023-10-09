package com.example.everytime.service;

import com.example.everytime.DTO.FreePost.UpdateFreeCommentDto;
import com.example.everytime.entity.FreeComment;
import com.example.everytime.entity.FreePost;
import com.example.everytime.entity.User;
import com.example.everytime.exception.AppException;
import com.example.everytime.exception.ErrorCode;
import com.example.everytime.repository.FreeCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FreeCommentService {
    private final FreeCommentRepository freeCommentRepository;
    private final UserService userService;

    @Transactional
    public FreeComment savedFreeComment(FreeComment freeComment) {
        return freeCommentRepository.save(freeComment);
    }

    public Page<FreeComment>  freeCommentList(FreePost findFreePost, int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return this.freeCommentRepository.findByFreeId(findFreePost, pageable);
    }

    @Transactional
    public void deletedFreeComment(int freeCommentId, String loginUser) {
        FreeComment findFreeComment = freeCommentRepository.findByFreeCmd(freeCommentId);
        checkLoginEqualCmtUser(findFreeComment, loginUser);
        freeCommentRepository.deleteById(freeCommentId);
    }

    @Transactional
    public FreeComment updatedFreeComment(int freeCmtId, UpdateFreeCommentDto updateFreeCommentDto, String loginUser) {
        FreeComment findFreeComment = freeCommentRepository.findByFreeCmd(freeCmtId);
        checkLoginEqualCmtUser(findFreeComment, loginUser);
        FreeComment updateFreeComment = FreeComment.updateFreeCmd(findFreeComment, updateFreeCommentDto);
        return updateFreeComment;
    }

    public void checkLoginEqualCmtUser(FreeComment freeComment, String loginUser) {
        User findUser = userService.getUserByUserId(loginUser);
        if(!freeComment.getFreeUser().equals(findUser)) {
            throw new AppException(ErrorCode.USER_NOT_MATCH);
        }
    }
}