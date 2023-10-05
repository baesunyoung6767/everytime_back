package com.example.everytime.entity;

import com.example.everytime.DTO.PrPost.PrCommentDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "pr_comment")
@Getter @Setter
public class PrComment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int prCmd;

    @ManyToOne
    @JoinColumn(name = "pr_id")
    private PrPost prId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User prUser;

    @Column(nullable = false)
    private String prCmtContent;

    @Column
    private LocalDateTime prCmdDate;

    public static PrComment createPrComment(PrCommentDto prCommentDto, User user, PrPost prPost) {
        PrComment prComment = new PrComment();
        prComment.setPrId(prPost);
        prComment.setPrUser(user);
        prComment.setPrCmtContent(prCommentDto.getPrCmtContent());
        prComment.setPrCmdDate(LocalDateTime.now());
        return prComment;
    }
}
