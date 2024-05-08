package com.codingrecipe.board.dto;

import com.codingrecipe.board.entity.ReservationEntity;
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


    public static ReservationDTO convertToDTO(ReservationEntity reservationEntity) {
        ReservationDTO reservationDTO = new ReservationDTO();

        reservationDTO.setDate(reservationEntity.getDate());
        reservationDTO.setTitle(reservationEntity.getTitle());
        reservationDTO.setSeat(reservationEntity.getSeat());


        return reservationDTO;
    }
}
