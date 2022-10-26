package com.stussy.stussyclone20220929.config;

import com.stussy.stussyclone20220929.handler.auth.AuthFailureHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity // 기존의 WebSecurityConfigurerAdapter 클래스를 해당 SecurityConfig로 대체하겠다.
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.httpBasic().disable(); // httpBasic() 인증방식 비활성화
        http.authorizeRequests() // 모든 요청시에 실행을 해라

                // ---------------Page---------------
                .antMatchers("/admin/**", "/api/admin/**")
                .access("hasRole('ADMIN') or hasRole('MANAGER')") // "" 권한이 필요
                .antMatchers("/accout") // 해당 요청 주소들은
                .access("hasRole('USER') or hasRole('ADMIN') or hasRole('MANAGER')")

                .antMatchers("/", "/index", "/collection/**")
                .permitAll()//
//                .authenticated() // 인증(로그인)을 통해 위 페이지에 들어가게 함(인증이 필요하다)
                .antMatchers("/account/login", "/account/register")
                .permitAll()

                // ---------------Resource---------------
                .antMatchers("/static/**", "/images/**")
                .permitAll()// 모두 접근 권한을 허용해라

                // ---------------Api---------------
                .antMatchers("/api/account/register", "/api/collections/**")
                .permitAll()

                .anyRequest() // antMatchers 외에 다른 모든 요청에
                .permitAll()
//                .denyAll() // 모든 접근을 차단해라


                // ---------------login---------------
                .and() // 그리고  // and 대신 http.로 다시 시작가능
                .formLogin() // 폼로그인 방식으로 인증을 해라
                .usernameParameter("email") // 기본 디폴트값이 username이기에 email로 변경
                .loginPage("/account/login") // 우리가 만든 로그인 페이지를 사용해라. GET 요청
                .loginProcessingUrl("/account/login") // 로그인 로직(PrincipalDetailService) POST 요청
                .failureHandler(new AuthFailureHandler())
                .defaultSuccessUrl("/index"); // 로그인 성공 후




    }
}
