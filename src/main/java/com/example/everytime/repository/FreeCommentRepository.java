package com.example.everytime.repository;

import com.example.everytime.entity.FreeComment;
import com.example.everytime.entity.FreePost;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FreeCommentRepository extends JpaRepository<FreeComment, Integer> {
    FreeComment findByFreeCmd(int freeCmd);
    Page<FreeComment> findByFreeId(FreePost freeId, Pageable pageable);
}