package com.codingrecipe.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/review")
public class ReviewController {

    @GetMapping
    public String review(Model model){
        return "/review/review";
    }

    @GetMapping("/add")
    public String reviewadd(Model model){
        return "/review/review-add";
    }

}
