package com.codingrecipe.board.login;

import com.codingrecipe.board.jwt.CustomUserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

        if (joinDTO.getPassword().equals("") || joinDTO.getUsername().equals("") || joinDTO.getPhonenumber().equals("")){
            return "redirect:/user/newUser";
        }

        if(!joinService.checkId(joinDTO.getUsername())){
            return "redirect:/user/newUser";
        }

        joinService.save(joinDTO);
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
