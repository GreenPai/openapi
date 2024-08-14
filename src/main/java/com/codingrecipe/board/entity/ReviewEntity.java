package com.codingrecipe.board.entity;

import com.codingrecipe.board.dto.ReviewDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

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

    public static ReviewEntity toSaveReviewEntity(ReviewDTO reviewDTO) {
        ReviewEntity entity = new ReviewEntity();
        entity.setTitle(reviewDTO.getTitle());
        entity.setStar(reviewDTO.getStar());
        entity.setCont(reviewDTO.getCont());
        return entity;
    }
}
