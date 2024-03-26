package com.codingrecipe.board.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class LoginController {


    @GetMapping("/newUser")
    public String newuser(){
        return "/login/newuser";
    }


}
