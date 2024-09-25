package com.codingrecipe.board.musical;

import com.codingrecipe.board.musical.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ReservationRepository extends JpaRepository<ReservationEntity, Long> {

    

    List<ReservationEntity> findByTitleAndDate(String title, String date);
    List<ReservationEntity> findByUser(String username);

    @Modifying
    @Transactional
    @Query("DELETE FROM ReservationEntity r WHERE r.date = :date AND r.musicalEntity.resno = :resno AND r.user = :user")
    void deleteByDateAndResnoAndUser(@Param("date") String date, @Param("resno") Long resno, @Param("user") String user);

}
