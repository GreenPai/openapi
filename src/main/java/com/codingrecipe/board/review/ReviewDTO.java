package com.codingrecipe.board.review;


import com.codingrecipe.board.musical.ReservationEntity;
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
    private String user;
    private String date;

    public static String titleToString(ReservationEntity entity) {
        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.setTitle(entity.getTitle());
        return reviewDTO.getTitle();
    }



}
