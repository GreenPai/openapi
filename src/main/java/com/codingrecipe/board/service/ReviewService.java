package com.codingrecipe.board.service;

import com.codingrecipe.board.dto.ReviewDTO;
import com.codingrecipe.board.entity.MusicalEntity;
import com.codingrecipe.board.entity.ReservationEntity;
import com.codingrecipe.board.entity.ReviewEntity;
import com.codingrecipe.board.repository.MusicalRepository;
import com.codingrecipe.board.repository.ReservationRepository;
import com.codingrecipe.board.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository repository;
    private final ReservationRepository reservationRepository;
    private final MusicalRepository musicalRepository;

    /**
     * @param username
     * 유저가 본 뮤지컬의 이름을 추출해주는 메서드입니다.
     * @return title
     */
    public List<String> findtitle(String username) {
        // List<ReservationEntity> reservationEntity = repository.findByUser(username);
        List<ReservationEntity> reservationEntity = reservationRepository.findByUser(username);
        List<String> list = new ArrayList<>();

        for(ReservationEntity entity : reservationEntity){
            String title = ReviewDTO.titleToString(entity);
            list.add(title);
        }

        // 중복 제거 작업 - Set으로 변환해서 중복을 없앰.
        Set<String> set = new HashSet<>(list);
        List<String> title = new ArrayList<>(set);

        return title;
    }

    /**
     * 리뷰 저장하는 메서드 입니다.
     * @param reviewDTO
     */
    public void saveReview(ReviewDTO reviewDTO) {
        List<MusicalEntity> MusicalEntitys = musicalRepository.findByTitle(reviewDTO.getTitle());
        ReviewEntity entity = ReviewEntity.toSaveReviewEntity(reviewDTO, MusicalEntitys.get(0));
        repository.save(entity);
    }
}
