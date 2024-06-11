package com.codingrecipe.board.service;

import com.codingrecipe.board.dto.MusicalDTO;
import com.codingrecipe.board.dto.ReservationDTO;
import com.codingrecipe.board.entity.MusicalEntity;
import com.codingrecipe.board.entity.ReservationEntity;
import com.codingrecipe.board.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;

    public List<ReservationDTO> findSeats(String title, String date) {

        List<ReservationEntity> reservationEntities  = reservationRepository.findByTitleAndDate(title,date);

        List<ReservationDTO> dtos = new ArrayList<>();

        for( ReservationEntity reservationEntity  :reservationEntities ){
            dtos.add(ReservationDTO.convertToDTO(reservationEntity));
        }

        return dtos;
    }

    public List<ReservationDTO> findReservation(String username) {

        List<ReservationEntity> reservationEntities  = reservationRepository.findByUser(username);
        List<ReservationDTO> reservationDTOS = new ArrayList<>();

        for( ReservationEntity reservationEntity : reservationEntities){
            reservationDTOS.add(ReservationDTO.convertToDTO(reservationEntity));
        }
        return reservationDTOS;

    }

    public String findPlaceByresno(Long res_no) {
        MusicalEntity musicalEntity = reservationRepository.findByRes_no(res_no);
        System.out.println("일단");

        String place = MusicalDTO.convertTitleToDTO(musicalEntity);

        return place;
    }

}
