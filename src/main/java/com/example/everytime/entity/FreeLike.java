package com.example.everytime.entity;

import com.example.everytime.DTO.FreePost.FreeLikeDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@Table(name = "free_like")
public class FreeLike {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int likeId;

    @ManyToOne
    @JoinColumn(name = "free_id")
    private FreePost freePost;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userId;

    @Column(nullable = false)
    private int isLiked = 0;

    static public FreeLike createdFreeLike(int isLiked, User user, FreePost freePost) {
        FreeLike newFreeLike = new FreeLike();
        newFreeLike.setIsLiked(isLiked);
        newFreeLike.setUserId(user);
        newFreeLike.setFreePost(freePost);
        return newFreeLike;
    }
}
