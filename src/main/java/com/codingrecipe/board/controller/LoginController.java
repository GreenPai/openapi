package com.codingrecipe.board.controller;

import com.codingrecipe.board.dto.JoinDTO;
import com.codingrecipe.board.service.JoinService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
@RequiredArgsConstructor

@RequestMapping("/user")
public class LoginController {

    private final JoinService joinService;

    @GetMapping("/newUser")
    public String newuser(){
        return "/login/newuser";
    }

    @GetMapping("/login")
    public String login(){return "/login/login";}

    @PostMapping("/getUser")
    public String getUser(@ModelAttribute JoinDTO joinDTO) throws IOException {
        joinService.save(joinDTO);
        return "index";
    }




}
