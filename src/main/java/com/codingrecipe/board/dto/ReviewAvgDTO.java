package com.codingrecipe.board.dto;


import com.codingrecipe.board.entity.ReviewEntity;
import lombok.*;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ReviewAvgDTO {

    private List<ReviewDTO> reviewDTOS;
    private Double avg;
    private Long cnt;

    public static ReviewAvgDTO toReviewAvg(List<ReviewEntity> reviewEntities){
        List<ReviewDTO> reviewDTOS = new ArrayList<>();
        Double sum = 0.0;

        for (ReviewEntity reviewEntity : reviewEntities) {
            ReviewDTO reviewDTO = new ReviewDTO();

            reviewDTO.setUser(reviewEntity.getUserEntity().getUsername());
            reviewDTO.setStar(reviewEntity.getStar());
            reviewDTO.setCont(reviewEntity.getCont());
            reviewDTO.setTitle(reviewEntity.getTitle());

            //날짜 정보
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm");
            String formattedDate = reviewEntity.getCreatedTime().format(formatter);
            reviewDTO.setDate(formattedDate);

            reviewDTOS.add(reviewDTO);
            sum += reviewEntity.getStar();
        }

        Long cnt = (long) reviewEntities.size();
        Double avg = sum/cnt;



        return new ReviewAvgDTO(reviewDTOS, avg, cnt);
    }
}
