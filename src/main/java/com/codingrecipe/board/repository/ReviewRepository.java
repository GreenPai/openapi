package com.codingrecipe.board.repository;

import com.codingrecipe.board.entity.ReservationEntity;
import com.codingrecipe.board.entity.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {


    List<ReviewEntity> findByResno(String resno);
}
