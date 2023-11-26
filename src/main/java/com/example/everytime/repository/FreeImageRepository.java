package com.example.everytime.repository;

import com.example.everytime.entity.FreeImage;
import com.example.everytime.entity.FreePost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FreeImageRepository extends JpaRepository<FreeImage, Integer> {
    FreeImage findById(int id);
    FreeImage findByFreePost(FreePost freePost);
}