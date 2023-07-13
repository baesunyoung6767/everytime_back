package com.example.everytime.config;

import com.example.everytime.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration 
@EnableWebSecurity // Spring Security 설정
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .httpBasic().disable()//기본 ui 사용, 사용하지 않을 시 disable()
                .csrf().disable()//REST API에서 csrf 보안이 필요없기 때문에 비활성화,
                .cors().and()
                .authorizeRequests()// 요청에 대한 사용 권한을 체크
                .antMatchers("/**").permitAll()//antMatchers 파라미터로 설정한 리소스 접근을 인증절차 없이 허용
                .antMatchers("user/register", "user/login").permitAll()//antMatchers 파라미터로 설정한 리소스 접근을 인증절차 없이 허용
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)//STATELESS로 설정함으로서 인증 정보를 서버에 담아두지 않음,JWT 토큰을 사용할 것이기 때문
                .and()
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}