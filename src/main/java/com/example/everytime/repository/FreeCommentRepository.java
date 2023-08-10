package com.example.everytime.repository;

import com.example.everytime.entity.FreeComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FreeCommentRepository extends JpaRepository<FreeComment, Integer> {
}
