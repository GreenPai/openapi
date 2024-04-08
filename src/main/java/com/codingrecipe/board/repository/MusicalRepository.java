package com.codingrecipe.board.repository;

import com.codingrecipe.board.entity.MusicalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface MusicalRepository extends JpaRepository<MusicalEntity, Long> {

    List<MusicalEntity> findByTitle(String title);

    //  List<MusicalEntity> findByDate(String date);



    //   List<MusicalEntity> findByOp_at(String op_at);


}
