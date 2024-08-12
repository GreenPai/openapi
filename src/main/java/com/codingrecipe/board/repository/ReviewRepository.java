package com.codingrecipe.board.repository;

import com.codingrecipe.board.entity.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<ReservationEntity, Long> {

    List<ReservationEntity> findByUser(String username);
}
