package com.codingrecipe.board.musical;

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
    private String place; // 뮤지컬 장소
    private Long res_no; // 뮤지컬 번호


    public static ReservationDTO convertToDTO(ReservationEntity reservationEntity) {
        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setUser(reservationEntity.getUser());
        reservationDTO.setDate(reservationEntity.getDate());
        reservationDTO.setTitle(reservationEntity.getTitle());
        reservationDTO.setSeat(reservationEntity.getSeat());

        // MusicalEntity의 Resno를 reservationDTO에 저장.
        if (reservationEntity.getMusicalEntity() != null) {
            reservationDTO.setRes_no(reservationEntity.getMusicalEntity().getResno());
        }

        return reservationDTO;

    }

    public void addSeat(String seat){
        this.seat_list.add(seat);
    }
}
