package com.codingrecipe.board.service;

import com.codingrecipe.board.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;

    // 제목, 날짜에 따른 데이터를 가져와야됨.
    public static List<String> findSeats(String title, String date) {
        return null;
    }
}
