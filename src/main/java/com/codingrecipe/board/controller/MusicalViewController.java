package com.codingrecipe.board.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MusicalViewController {

    @GetMapping("api_page")
    public String api_page(){

        return "api_list";


    }


}
