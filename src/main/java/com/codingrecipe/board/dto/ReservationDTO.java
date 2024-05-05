package com.codingrecipe.board.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class ReservationDTO {

    private String title;
    private String date;
    private int price;
    private String seat;


}
