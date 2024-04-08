package com.codingrecipe.board.controller;

import com.codingrecipe.board.dto.JoinDTO;
import com.codingrecipe.board.entity.UserEntity;
import com.codingrecipe.board.jwt.JWTUtil;
import com.codingrecipe.board.jwt.LoginFilter;
import com.codingrecipe.board.repository.UserRepository;
import com.codingrecipe.board.service.CustomUserDetailService;
import com.codingrecipe.board.service.JoinService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

@Controller
@RequiredArgsConstructor

@RequestMapping("/user")
public class LoginController {

    private final JoinService joinService;
    private final UserRepository userRepository;
    private final CustomUserDetailService customUserDetailService;
    private final UserDetailsService userDetailsService;

    @GetMapping("/newUser")
    public String newuser(){
        return "/login/newuser";
    }

    @GetMapping("/loginpage")
    public String login(){return "/login/login";}


    @PostMapping("/getUser")
    public String getUser(@ModelAttribute JoinDTO joinDTO) throws IOException {
        joinService.save(joinDTO);

        return "index";
    }

    @PostMapping("/login")
    public String login(JoinDTO joinDTO){

        System.out.println("인덱스 실행");
        return "index";
    }

    @GetMapping("/loginpage2")
    public String login2(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iter = authorities.iterator();
        GrantedAuthority auth = iter.next();
        String role = auth.getAuthority();

        System.out.println(role);
        return "index";}


    @GetMapping("/jwttest")
    public String logintest(){
        return "/login/jwttest";
    }



}
