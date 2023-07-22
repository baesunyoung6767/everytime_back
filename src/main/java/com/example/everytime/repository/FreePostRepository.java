package com.example.everytime.repository;

import com.example.everytime.entity.FreePost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FreePostRepository extends JpaRepository<FreePost, Long> {
    // 제목으로 게시글 찾는 거 코드 추가할 것
}
