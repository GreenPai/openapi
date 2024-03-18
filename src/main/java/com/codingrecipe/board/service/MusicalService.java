package com.codingrecipe.board.service;

import com.codingrecipe.board.dto.MusicalDTO;
import com.codingrecipe.board.entity.MusicalEntity;
import com.codingrecipe.board.repository.MusicalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MusicalService {
    private final MusicalRepository musicalRepository;

    public void save(MusicalDTO musicalDTO){
        MusicalEntity musicalEntity = MusicalEntity.toSaveEntity(musicalDTO);
        musicalRepository.save(musicalEntity);

    }
}
