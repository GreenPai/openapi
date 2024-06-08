package com.codingrecipe.board.repository;

import com.codingrecipe.board.entity.MusicalEntity;
import com.codingrecipe.board.entity.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<ReservationEntity, Long> {

    

    List<ReservationEntity> findByTitleAndDate(String title, String date);

    List<ReservationEntity> findByUser(String username);

    MusicalEntity findByTitle(String title);
}
