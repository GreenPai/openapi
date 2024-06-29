package com.codingrecipe.board.service;

import com.codingrecipe.board.dto.MusicalDTO;
import com.codingrecipe.board.dto.ReservationDTO;
import com.codingrecipe.board.entity.BoardEntity;
import com.codingrecipe.board.entity.CommentEntity;
import com.codingrecipe.board.entity.MusicalEntity;
import com.codingrecipe.board.entity.ReservationEntity;
import com.codingrecipe.board.repository.MusicalRepository;
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
    private final MusicalRepository musicalRepository;

    public void deleteMusical(String date, Long resno) {
        // reservationRepository.deleteByResnoAndDate(date,resno);

    }



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
        // 이 부분에서 resno를 기준으로 검색했기에 여러가지 값이 나올 수 있음.
        MusicalEntity musicalEntity = musicalRepository.findByResno(res_no);
        MusicalDTO musicalDTO = MusicalDTO.convertToDTO(musicalEntity);
        System.out.println(musicalDTO.getPlace_nm());

        return musicalDTO.getPlace_nm();
    }

}
