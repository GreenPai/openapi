package com.codingrecipe.board.entity;

import com.codingrecipe.board.dto.ReservationDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "musical_reservation")
@Setter
@Getter
public class ReservationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increment
    private Long num;

    @Column
    private String title;
    @Column
    private String date;
    @Column
    private int price;
    @Column
    private String seat;
    @Column
    private String user;

    public static ReservationEntity toSaveEntity(ReservationDTO reservationDTO) {
        ReservationEntity reservationEntity = new ReservationEntity();
        reservationEntity.setUser(reservationDTO.getUser());
        reservationEntity.setDate(reservationDTO.getDate());
        reservationEntity.setPrice(reservationDTO.getPrice());
        reservationEntity.setSeat(reservationDTO.getSeat());
        reservationEntity.setTitle(reservationDTO.getTitle());


        return reservationEntity;
    }
}
