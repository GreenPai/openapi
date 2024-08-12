package com.codingrecipe.board.dto;


import com.codingrecipe.board.entity.ReservationEntity;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDTO {

    private Long num;
    private String title;
    private String cont;
    private Double star;

    public static String titleToString(ReservationEntity entity) {
        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.setTitle(entity.getTitle());
        return reviewDTO.getTitle();
    }
}
