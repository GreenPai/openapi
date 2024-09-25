package com.codingrecipe.board.review;

import com.codingrecipe.board.review.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {
    List<ReviewEntity> findByMusicalEntity_Resno(Long resno);
}
