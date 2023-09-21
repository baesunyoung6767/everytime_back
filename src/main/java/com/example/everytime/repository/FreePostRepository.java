package com.example.everytime.repository;

import com.example.everytime.entity.FreePost;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FreePostRepository extends JpaRepository<FreePost, Integer> {
    FreePost findByFreeId(int freeId);
    List<FreePost> findByFreeTitleContaining(String freeTitle);
    Page<FreePost> findAll(Pageable pageable);

}
