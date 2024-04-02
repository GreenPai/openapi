package com.codingrecipe.board.controller;

import com.codingrecipe.board.dto.JoinDTO;
import com.codingrecipe.board.service.JoinService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;
import java.util.Iterator;

@Controller
public class JoinController {

    private final JoinService joinService;


    public JoinController(JoinService joinService) {

        this.joinService = joinService;
    }


    @PostMapping("/logintest")
    public String logintest(JoinDTO joinDTO){

        JoinDTO joinDTO1 = joinService.test(joinDTO);
        System.out.println(joinDTO1.getUsername());
        System.out.println(joinDTO1.getPassword());

        return "index";
    }


    /*
    @PostMapping("/join")
    public String joinProcess(JoinDTO joinDTO) {

        joinService.joinProcess(joinDTO);


        return "ok";
    }
    */

    /*
    @PostMapping("/login")
    public String login(JoinDTO joinDTO){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iter = authorities.iterator();
        GrantedAuthority auth = iter.next();
        String role = auth.getAuthority();

        System.out.println(role);


        return "index";
    }

     */
}
