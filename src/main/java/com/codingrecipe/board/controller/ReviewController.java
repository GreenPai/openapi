package com.codingrecipe.board.controller;

import com.codingrecipe.board.dto.ReviewDTO;
import com.codingrecipe.board.service.ReservationService;
import com.codingrecipe.board.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/review")
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping
    public String review(Model model){
        return "/review/review";
    }

    @GetMapping("/add")
    public ModelAndView reviewmove(){
        String username = "ehfbs";
        List<String> titleList = reviewService.findtitle(username);
        System.out.println(titleList);

        ModelAndView mv = new ModelAndView();
        mv.setViewName("/review/review-add");
        mv.addObject("list", titleList);
        return mv;
    }

    @PostMapping("/add")
    public ModelAndView reviewadd(ReviewDTO reviewDTO){

        reviewService.saveReview(reviewDTO);

        ModelAndView mv = new ModelAndView();
        mv.setViewName("index");
        return mv;
    }

}
