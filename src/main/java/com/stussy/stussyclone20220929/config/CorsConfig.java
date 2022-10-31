package com.stussy.stussyclone20220929.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");       // 모든 주소 허용
        config.addAllowedHeader("*");       // 모든 헤더 내용 허용
        config.addAllowedMethod("*");       // 모든 메소드 허용
        source.registerCorsConfiguration("/**", config);    //  모든 요청이 이 설정을 적용
        return new CorsFilter(source);
    }
    // 다른 방법으로는 자체 api에 어노테이션으로 달아주는 것
    // 리액트는 자체 포트를 쓰기에 나중에 많이 쓰임
}
