package com.example.everytime.entity;

import com.example.everytime.DTO.FreeCommentDto;
import com.example.everytime.DTO.UpdateFreeCommentDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@Table(name = "free_comment")
@ToString
public class FreeComment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int freeCmd;

    @ManyToOne
    @JoinColumn(name = "free_id")
    private FreePost freeId;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User freeUser;

    @Column(nullable = false)
    private String freeCmdContent;

    @Column
    private LocalDateTime freeCmdDate;

    public static FreeComment createFreeCmd(FreeCommentDto freeCommentDto, User user, FreePost freePost) {
        FreeComment freeComment = new FreeComment();
        freeComment.setFreeId(freePost);
        freeComment.setFreeUser(user);
        freeComment.setFreeCmdContent(freeCommentDto.getFreeCmdContent());
        freeComment.setFreeCmdDate(LocalDateTime.now());

        return freeComment;
    }

    public static FreeComment updateFreeCmd(FreeComment freeComment, UpdateFreeCommentDto updateFreeCommentDto) {
        freeComment.setFreeCmdContent(updateFreeCommentDto.getUpdateContent());
        return freeComment;
    }
}
