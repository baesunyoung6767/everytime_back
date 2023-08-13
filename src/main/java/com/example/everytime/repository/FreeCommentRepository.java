package com.example.everytime.repository;

import com.example.everytime.entity.FreeComment;
import com.example.everytime.entity.FreePost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FreeCommentRepository extends JpaRepository<FreeComment, Integer> {
    List<FreeComment> findByFreeId(FreePost freeId);
    FreeComment findByFreeCmd(int freeCmd);
}
