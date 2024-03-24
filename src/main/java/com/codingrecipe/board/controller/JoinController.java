package com.codingrecipe.board.controller;

import com.codingrecipe.board.dto.JoinDTO;
import com.codingrecipe.board.service.JoinService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
// @RequiredArgsConstructor
public class JoinController {

    private final JoinService joinService;


    public JoinController(JoinService joinService) {

        this.joinService = joinService;
    }



    @PostMapping("/join")
    public String joinProcess(JoinDTO joinDTO) {

        joinService.joinProcess(joinDTO);


        return "ok";
    }

}
