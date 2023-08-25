package com.example.everytime.entity;

import com.example.everytime.DTO.PrPost.PrPostDto;
import com.example.everytime.DTO.PrPost.PrUpdateRequestDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@ToString
@Table(name = "pr_board")
public class PrPost {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int prId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User prUser;

    @Column(nullable = false)
    private String prTitle;

    @Column(nullable = false)
    private String prContent;

    @Column
    private LocalDateTime prDate;

    public static PrPost createPrPost(PrPostDto prPostDto, User user) {
        PrPost newPrPost = new PrPost();
        newPrPost.setPrUser(user);
        newPrPost.setPrTitle(prPostDto.getPrTitle());
        newPrPost.setPrContent(prPostDto.getPrContent());
        newPrPost.setPrDate(LocalDateTime.now());
        return newPrPost;
    }

    public static PrPost updatePrPost(PrPost prPost, PrUpdateRequestDto prUpdateRequestDto) {
        prPost.setPrTitle(prUpdateRequestDto.getTitle());
        prPost.setPrContent(prUpdateRequestDto.getContent());
        return prPost;
    }
}
