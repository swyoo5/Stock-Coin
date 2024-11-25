package com.example.demo.config;

import jakarta.servlet.DispatcherType;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public WebSecurityCustomizer configure() {
        // 정적 리소스 필터 미적용
        return (web) -> web.ignoring()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                // csrf 방어를 비활성화 -> 보안이 중요한 앱에서는 보호를 유지해야함
                .csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable())
                // corf 비활성화 => 브라우저의 동일 출처 정책을 제어
                .cors(httpSecurityCorsConfigurer -> httpSecurityCorsConfigurer.disable())
                // forward 요청에 대해 모든 사용자가 접근 가능
                .authorizeHttpRequests(authorizeHttpRequestsConfigurer -> authorizeHttpRequestsConfigurer
                        .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
                        // 작성된 경로에 대한 접근을 모든 사용자에게 허용
                        .requestMatchers("/login", "/sinup", "/user/**", "/").permitAll()
                        // 해당 경로에 접근하려면 admin 권한이 있어야 함
                        .requestMatchers("/admin/**").hasAuthority("ADMIN")
                        // 나머지 모든 요청은 인증된 사용자만 접근
                        .anyRequest().authenticated())
                .formLogin(formLoginConfigurer -> formLoginConfigurer
                        // 사용자 정의 로그인 페이지 설정
                        .loginPage("/user/login")
                        // 로그인 처리를 수행할 URL 입력
                        .loginProcessingUrl("/loginProcess")
                        // 로그인 폼에서 사용자 이름 입력 필드의 이름 지정
                        .usernameParameter("username")
                        // 로그인 폼에서 비밀번호 입력 필드의 이름을 지정
                        .passwordParameter("password")
                        // 로그인 성공 시 이동할 기본 페이지
                        .defaultSuccessUrl("/")
                        .permitAll())
                // 로그아웃 설정
                .logout(logoutConfigurer -> logoutConfigurer
                        .logoutUrl("/logout") // 로그아웃을 처리할 URL 설정
                        .logoutSuccessUrl("/") // 로그아웃 성공 시 이동할 페이지
                        .invalidateHttpSession(true) // 로그아웃 시 세션을 무효화
                        .clearAuthentication(true)) // 로그아웃 시 인증 정보 삭제
                .build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder, UserDetailsService userDetailService, AuthenticationManagerBuilder authenticationManagerBuilder)
    throws Exception {
//        AuthenticationManagerBuilder authenticationMangerBuilder =
//                http.getSharedObject(AuthenticationManagerBuilder.class);
//        // 사용자 정보를 userDetailsService에서 로드 & 비밀번호를 비교할 때 암호화된 비밀번호를 사용할 수 있도록 설정
//        authenticationManagerBuilder.userDetailsService(userDetailService).passwordEncoder(bCryptPasswordEncoder);
//        return authenticationManagerBuilder.build();

        return http.getSharedObject(AuthenticationManager.class);
    }
}
