package com.example.everytime.repository;

import com.example.everytime.entity.PrComment;
import com.example.everytime.entity.PrPost;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrCommentRepository extends JpaRepository<PrComment, Integer> {
    Page<PrComment> findByPrId(PrPost prId, Pageable pageable);
}
