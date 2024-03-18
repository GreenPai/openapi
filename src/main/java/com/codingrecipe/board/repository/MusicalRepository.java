package com.codingrecipe.board.repository;

import com.codingrecipe.board.entity.CommentEntity;
import com.codingrecipe.board.entity.MusicalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MusicalRepository extends JpaRepository<MusicalEntity, Long> {
}
