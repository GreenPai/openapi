package com.codingrecipe.board.point;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor

@RequestMapping("/point")
public class PointController {
    private final PointService pointService;

    @GetMapping("/{userId}")
    public String Point(@PathVariable("userId") long userId, Model model){
        Point point = PointService.findByUser(userId);
        return "index";
    }



}
