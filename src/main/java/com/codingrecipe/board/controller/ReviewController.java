package com.codingrecipe.board.controller;

import com.codingrecipe.board.dto.MusicalDTO;
import com.codingrecipe.board.dto.ReviewDTO;
import com.codingrecipe.board.service.MusicalService;
import com.codingrecipe.board.service.ReservationService;
import com.codingrecipe.board.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/review")
public class ReviewController {

    private final ReviewService reviewService;
    private final MusicalService musicalService;

    @GetMapping
    public String review(Model model){
        return "/review/review";
    }

    // 인덱스에서 리뷰 추가.
    // 전체 예약된 뮤지컬 리스트를 다 보여줌
    @GetMapping("/add")
    public ModelAndView reviewmove(@RequestParam("user") String user){
        List<String> titleList = reviewService.findtitle(user);
        System.out.println(titleList);

        ModelAndView mv = new ModelAndView();
        mv.setViewName("/review/review-add");
        mv.addObject("list", titleList);
        return mv;
    }

    @PostMapping("/add")
    public ModelAndView reviewAddV1(ReviewDTO reviewDTO){

        reviewService.saveReview(reviewDTO);

        ModelAndView mv = new ModelAndView();
        mv.setViewName("index");
        return mv;
    }

    @GetMapping("/add/{resno}")
    public ModelAndView reviewAddV2(@PathVariable("resno") Long resno){

        MusicalDTO dto = musicalService.findByResno(resno);

        List<String> title = new ArrayList<>();
        title.add(dto.getTitle());

        ModelAndView mv = new ModelAndView();
        mv.setViewName("/review/review-add");
        mv.addObject("list", title);
        return mv;
    }

}
