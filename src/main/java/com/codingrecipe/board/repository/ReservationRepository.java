package com.codingrecipe.board.repository;

import com.codingrecipe.board.entity.MusicalEntity;
import com.codingrecipe.board.entity.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<ReservationEntity, Long> {

    

    List<ReservationEntity> findByTitleAndDate(String title, String date);

    List<ReservationEntity> findByUser(String username);

    Optional<MusicalEntity> findByMusicalEntity_Resno(Long resNo);
}
