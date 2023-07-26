package com.example.everytime.entity;

import com.example.everytime.DTO.FreePostDto;
import com.example.everytime.DTO.UpdateFreeDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@Table(name = "free_board")
@ToString
public class FreePost{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int freeId;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user; // 작성자 아이디

    @Column(nullable = false)
    private String freeTitle;

    @Column(nullable = false)
    private String freeContent;

    @Column
    private LocalDateTime freeDate;

    public static FreePost createFreePost(FreePostDto freePostDto, User user) {
        FreePost freePost = new FreePost();
        freePost.setFreeContent(freePostDto.getFreeContent());
        freePost.setUser(user);
        freePost.setFreeTitle(freePostDto.getFreeTitle());
        freePost.setFreeDate(LocalDateTime.now());
        return freePost;
    }

    public static FreePost updateFreePost(FreePost freePost, UpdateFreeDto updateFreeDto) {
        freePost.setFreeTitle(updateFreeDto.getFreeTitle());
        freePost.setFreeContent(updateFreeDto.getFreeContent());
        return freePost;
    }
}
