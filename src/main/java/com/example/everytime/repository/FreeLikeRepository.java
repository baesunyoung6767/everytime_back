package com.example.everytime.repository;

import com.example.everytime.entity.FreeLike;
import com.example.everytime.entity.FreePost;
import com.example.everytime.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FreeLikeRepository extends JpaRepository<FreeLike, Integer> {
    FreeLike findByFreePostAndUserId(FreePost FreePost, User userId);
    Page<FreeLike> findByUserId(User userId, Pageable pageable);
}
