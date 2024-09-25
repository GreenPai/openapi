package com.codingrecipe.board.review;

import com.codingrecipe.board.musical.MusicalEntity;
import com.codingrecipe.board.musical.ReservationEntity;
import com.codingrecipe.board.login.UserEntity;
import com.codingrecipe.board.musical.MusicalRepository;
import com.codingrecipe.board.musical.ReservationRepository;
import com.codingrecipe.board.login.UserRepository;
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
    private final UserRepository userRepository;

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
        UserEntity userEntity = userRepository.findByUsername(reviewDTO.getUser());
        ReviewEntity entity = ReviewEntity.toSaveReviewEntity(reviewDTO, MusicalEntitys.get(0), userEntity);
        repository.save(entity);
    }

    public ReviewAvgDTO findByResno(Long resno) {
        List<ReviewEntity> reviewEntities = repository.findByMusicalEntity_Resno(resno);
        // List<ReviewDTO> reviewDTOS = ReviewDTO.toSaveReviewDTOD(reviewEntities);
        ReviewAvgDTO reviewAvg = ReviewAvgDTO.toReviewAvg(reviewEntities);

        return reviewAvg;

    }
}
