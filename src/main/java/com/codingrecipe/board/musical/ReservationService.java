package com.codingrecipe.board.musical;

import com.codingrecipe.board.musical.MusicalDTO;
import com.codingrecipe.board.musical.ReservationDTO;
import com.codingrecipe.board.musical.MusicalEntity;
import com.codingrecipe.board.musical.ReservationEntity;
import com.codingrecipe.board.musical.MusicalRepository;
import com.codingrecipe.board.musical.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final MusicalRepository musicalRepository;

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

    public void deleteMusical(String date, Long resno, String user) {
        reservationRepository.deleteByDateAndResnoAndUser(date,resno,user);
    }
}
