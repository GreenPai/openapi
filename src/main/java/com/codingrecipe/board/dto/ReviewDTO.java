package com.codingrecipe.board.dto;


import com.codingrecipe.board.entity.ReservationEntity;
import com.codingrecipe.board.entity.ReviewEntity;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

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
    private String user;

    public static String titleToString(ReservationEntity entity) {
        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.setTitle(entity.getTitle());
        return reviewDTO.getTitle();
    }



}
