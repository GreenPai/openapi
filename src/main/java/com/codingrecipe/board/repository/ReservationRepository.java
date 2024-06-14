package com.codingrecipe.board.repository;

import com.codingrecipe.board.entity.MusicalEntity;
import com.codingrecipe.board.entity.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<ReservationEntity, Long> {

    

    List<ReservationEntity> findByTitleAndDate(String title, String date);

    List<ReservationEntity> findByUser(String username);

    // ReservationEntity findByMusical_Res_no(Long resNo);

    //엔티티 사용시 조인되지 않은 엔티티는 오류 발생.
    // ReservationEntity findByMusical_Res_no(Long resNo);

}
