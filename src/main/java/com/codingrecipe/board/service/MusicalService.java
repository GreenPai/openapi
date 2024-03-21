package com.codingrecipe.board.service;

import com.codingrecipe.board.dto.BoardDTO;
import com.codingrecipe.board.dto.MusicalDTO;
import com.codingrecipe.board.entity.BoardEntity;
import com.codingrecipe.board.entity.MusicalEntity;
import com.codingrecipe.board.repository.MusicalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MusicalService {
    private final MusicalRepository musicalRepository;



    // addNewMusical : 제목을 비교해서 없는 중복 저장하지 않는다.
    // 현재 컨트롤러에서 정해진 양 만을 들고오기에 새롭게 추가되는 데이터는 없기에 else문에 데이터 추가 로직을 만들지 않음.

    public void addNewMusical(MusicalDTO musicalDTO) {
        List<MusicalEntity> existingMusicals = musicalRepository.findByTitle(musicalDTO.getTitle());

        // 제목이 없을때 바로 저장.
        if(existingMusicals.isEmpty()){
            MusicalEntity musicalEntity = MusicalEntity.toSaveEntity(musicalDTO);
            musicalRepository.save(musicalEntity);
        }else {
            for(MusicalEntity existingMusical : existingMusicals){
                MusicalDTO musicalDTO1 = MusicalDTO.convertToDTO(existingMusical);
                if(musicalDTO1.getTitle().equals(musicalDTO.getTitle())){

                }

            }

        }

    }

    public void save(MusicalDTO musicalDTO){
        MusicalEntity musicalEntity = MusicalEntity.toSaveEntity(musicalDTO);
        musicalRepository.save(musicalEntity);
    }


    @Transactional
    public List<MusicalDTO> findAll() {

        List<MusicalEntity> musicalEntityList = musicalRepository.findAll();
        List<MusicalDTO> musicalDTOList =new ArrayList<>();
        for (MusicalEntity musicalEntity: musicalEntityList){
            musicalDTOList.add(MusicalDTO.convertToDTO(musicalEntity));
        }
        return musicalDTOList;



    }

    @Transactional
    public MusicalDTO findByTitle(String title) {

        List<MusicalEntity> musicalEntity = musicalRepository.findByTitle(title);
        MusicalDTO musicalDTO = MusicalDTO.convertToDTO(musicalEntity.get(0));
        return musicalDTO;

    }
}
