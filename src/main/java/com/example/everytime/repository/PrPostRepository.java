package com.example.everytime.repository;

import com.example.everytime.entity.PrPost;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrPostRepository extends JpaRepository<PrPost, Integer> {
    @Override
    Page<PrPost> findAll(Pageable pageable);
    PrPost findByPrId(int prId);

    Page<PrPost> findByPrTitleContaining(Pageable pageable, String postTitle);
}
