package com.example.everytime.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "free_image")
public class FreeImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "freeId")
    private FreePost freePost;

    @Column(nullable = false)
    private String originalFileName;

    @Column(nullable = false)
    private String storedFileName;

    @Column(nullable = false)
    private long fileSize;

    @Column
    private LocalDateTime freeDate;
}
