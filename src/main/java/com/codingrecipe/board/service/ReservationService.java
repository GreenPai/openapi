package com.codingrecipe.board.service;

import com.codingrecipe.board.dto.MusicalDTO;
import com.codingrecipe.board.dto.ReservationDTO;
import com.codingrecipe.board.entity.BoardEntity;
import com.codingrecipe.board.entity.CommentEntity;
import com.codingrecipe.board.entity.MusicalEntity;
import com.codingrecipe.board.entity.ReservationEntity;
import com.codingrecipe.board.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        Optional<MusicalEntity> optionalMusicalEntity = reservationRepository.findByMusicalEntity_Resno(res_no);
        if (optionalMusicalEntity.isPresent()) {
            MusicalEntity musicalEntity = optionalMusicalEntity.get();
            String place = MusicalDTO.convertToDTOTitle(musicalEntity);
            System.out.println(place);
            System.out.println("find");
            return place;
        } else {
            return null;
        }

    }

}
