package com.codingrecipe.board.musical;

import com.codingrecipe.board.musical.MusicalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MusicalRepository extends JpaRepository<MusicalEntity, Long> {

    List<MusicalEntity> findByTitle(String title);
    MusicalEntity findByResno(Long resNo);
}
