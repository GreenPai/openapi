package com.codingrecipe.board.dto;

import com.codingrecipe.board.entity.ReservationEntity;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

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
    private String user;

    // 마이페이지
    private int count = 1 ; // 예약 갯수
    private List<String> seat_list = new ArrayList<>(); // 좌석 배열 (c1, c2)

    public static ReservationDTO convertToDTO(ReservationEntity reservationEntity) {
        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setUser(reservationEntity.getUser());
        reservationDTO.setDate(reservationEntity.getDate());
        reservationDTO.setTitle(reservationEntity.getTitle());
        reservationDTO.setSeat(reservationEntity.getSeat());

        return reservationDTO;
    }

    public void addSeat(String seat){
        this.seat_list.add(seat);
    }
}
