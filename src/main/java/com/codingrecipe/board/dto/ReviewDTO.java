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

    private Double total;

    public static String titleToString(ReservationEntity entity) {
        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.setTitle(entity.getTitle());
        return reviewDTO.getTitle();
    }

    public static List<ReviewDTO> toSaveReviewDTOD(List<ReviewEntity> reviewEntities) {
        List<ReviewDTO> reviewDTOS = new ArrayList<>();
        Double sum = 0.0;

        for( ReviewEntity reviewEntity : reviewEntities){
            ReviewDTO reviewDTO = new ReviewDTO();

            reviewDTO.setUser(reviewEntity.getUserEntity().getUsername());
            reviewDTO.setStar(reviewEntity.getStar());
            reviewDTO.setCont(reviewEntity.getCont());
            reviewDTO.setTitle(reviewEntity.getTitle());
            sum += reviewEntity.getStar();

        }

            // model.addAttribute("averageRating", average);
        return reviewDTOS;
    }
}
