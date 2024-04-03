package com.codingrecipe.board.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsMvcConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {

        registry.addMapping("/**") // 모든 엔드포인트에 대해 CORS를 허용합니다.
                .allowedOrigins("*"); // 모든 출처에서의 요청을 허용합니다. 필요에 따라 특정 출처를 지정할 수도 있습니다.

    }
}