package com.codingrecipe.board.review;

import com.codingrecipe.board.musical.MusicalDTO;
import com.codingrecipe.board.jwt.filter.JWTUtil;
import com.codingrecipe.board.musical.MusicalService;
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
    private final JWTUtil jwtUtil;

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

    // 리뷰 저장하는 기능
    @PostMapping("/add")
    public ModelAndView reviewAddV1(ReviewDTO reviewDTO){
        String jwt = reviewDTO.getUser().split(" ")[1];
        String user = jwtUtil.getUsername(jwt);
        reviewDTO.setUser(user);

        reviewService.saveReview(reviewDTO);

        ModelAndView mv = new ModelAndView();
        mv.setViewName("index");
        return mv;
    }

    // 지정해둔 뮤지컬의 리뷰 작성 페이지 제공
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

    @GetMapping("/{resno}")
    public String reviewPage(@PathVariable("resno") Long resno, Model model){

        ReviewAvgDTO reviewAvgDTO = reviewService.findByResno(resno);
        Double avg = Double.valueOf(String.format("%.2f", reviewAvgDTO.getAvg()));

        model.addAttribute("cnt" , reviewAvgDTO.getCnt());
        model.addAttribute("avg", avg);
        model.addAttribute("reviews" , reviewAvgDTO.getReviewDTOS());

        System.out.println(reviewAvgDTO);
        return "/review/review";
    }







}
