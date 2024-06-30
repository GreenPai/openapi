package com.codingrecipe.board.repository;

import com.codingrecipe.board.entity.MusicalEntity;
import com.codingrecipe.board.entity.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<ReservationEntity, Long> {

    

    List<ReservationEntity> findByTitleAndDate(String title, String date);

    List<ReservationEntity> findByUser(String username);

    Optional<MusicalEntity> findByMusicalEntity_Resno(Long resNo);

    @Modifying
    @Transactional
    @Query("DELETE FROM ReservationEntity r WHERE r.date = :date AND r.musicalEntity.resno = :resno AND r.user = :user")
    void deleteByDateAndResnoAndUser(@Param("date") String date, @Param("resno") Long resno, @Param("user") String user);

}
