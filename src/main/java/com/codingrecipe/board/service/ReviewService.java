package com.codingrecipe.board.service;

import com.codingrecipe.board.dto.ReviewDTO;
import com.codingrecipe.board.entity.ReservationEntity;
import com.codingrecipe.board.repository.ReservationRepository;
import com.codingrecipe.board.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository repository;

    /**
     * @param username
     * 유저가 본 뮤지컬의 이름을 추출해주는 메서드입니다.
     * @return title
     */
    public List<String> findtitle(String username) {
        List<ReservationEntity> reservationEntity = repository.findByUser(username);
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

    public void saveReview(ReviewDTO reviewDTO) {

    }
}
