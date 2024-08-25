package com.codingrecipe.board.entity;

import com.codingrecipe.board.dto.ReviewDTO;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "review")
public class ReviewEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long num;

    @Column
    private String title;
    @Column
    private String cont;
    @Column
    private Double star;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resno") // 외래 키 컬럼 정의
    private MusicalEntity musicalEntity;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userid") // 외래 키 컬럼 정의
    private UserEntity userEntity;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdTime;

    public static ReviewEntity toSaveReviewEntity(ReviewDTO reviewDTO, MusicalEntity musicalEntity, UserEntity userEntity) {
        ReviewEntity entity = new ReviewEntity();
        entity.setTitle(reviewDTO.getTitle());
        entity.setStar(reviewDTO.getStar());
        entity.setCont(reviewDTO.getCont());
        entity.setMusicalEntity(musicalEntity);
        entity.setUserEntity(userEntity);
        return entity;
    }
}
