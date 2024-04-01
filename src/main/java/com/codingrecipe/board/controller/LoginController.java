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
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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

    /*
    @GetMapping("/login")
    public ResponseEntity<String> loginpost(JoinDTO joinDTO, HttpServletRequest request, HttpServletResponse response){


        System.out.println(joinDTO.getUsername());
        System.out.println(joinDTO.getPassword());
        String name = joinDTO.getUsername();
        String pwd  = joinDTO.getPassword();

        System.out.println(request);
        System.out.println(response);


        UserDetails userDetails = userDetailsService.loadUserByUsername(name);

        return "index";
    }

*/

    @PostMapping("/getUser")
    public String getUser(@ModelAttribute JoinDTO joinDTO) throws IOException {
        joinService.save(joinDTO);
        return "index";
    }




}
