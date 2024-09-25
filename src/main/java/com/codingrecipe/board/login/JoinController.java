package com.codingrecipe.board.login;

import com.codingrecipe.board.jwt.filter.JWTUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class JoinController {

    private final JoinService joinService;
    private final JWTUtil jwtUtil;

    public JoinController(JoinService joinService, JWTUtil jwtUtil) {
        this.joinService = joinService;
        this.jwtUtil = jwtUtil;
    }


    @PostMapping("/logintest")
    public String logintest(JoinDTO joinDTO){

        JoinDTO joinDTO1 = joinService.test(joinDTO);

        return "index";
    }

    // 로그인 좌석 예약 페이지 musical_sit.html 아이디 체크
    @GetMapping("/loginCheck")
    @ResponseBody
    public Map<String, String> loginCheck(@RequestParam("user") String token) {

        // 토큰을 검증하고 필요한 데이터를 반환하는 로직
        // token의 경우 Bearer eyJhbGciOiJIUzI1NiJ9.eyJ1c2VybmFtZSI6ImFkbWluMTIzIiwicm9sZSI6IuydvOuwmOyduCIsImlhdCI6MTcxNjM3NDU3NSwiZXhwIjoxNzE2NDEwNTc1fQ.AUcuJXLFmdY4yqj7NP0sGN2g6xrOPw3xzFKxulIGqlA
        // Bearer 이 부분을 분리해야된다. 분리하지 않으면 오류가 발생.
        String Real_token = token.split(" ")[1];
        String username = jwtUtil.getUsername(Real_token);

        Map<String, String> response = new HashMap<>();
        response.put("username", username);

        return response;
    }


}
