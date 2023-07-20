//package com.example.everytime.entity;
//
//import lombok.Getter;
//import lombok.Setter;
//import lombok.ToString;
//
//import javax.persistence.*;
//import java.time.LocalDateTime;
//
//@Entity
//@Getter @Setter
//@Table(name = "free_board")
//@ToString
//public class freePost {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private int freeId;
//
//    @OneToMany
//    @JoinColumn(name="user_id")
//    private String freeUser; // 작성자 아이디
//
//    @Column(nullable = false)
//    private String freeTitle;
//
//    @Column(nullable = false)
//    private String freeContent;
//
//    @Column(nullable = false)
//    private LocalDateTime freeDate; // 작성일
//}
