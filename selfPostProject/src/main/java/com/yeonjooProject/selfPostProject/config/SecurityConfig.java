package com.yeonjooProject.selfPostProject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

//@Configuration
//public class SecurityConfig {
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/h2-console/**").permitAll() // H2 콘솔 접근 허용
//                        .anyRequest().authenticated() // 나머지 요청은 인증 필요
//                )
//                .csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**")) // H2 Console CSRF 비활성화
//                .headers(headers -> headers
//                        .frameOptions().sameOrigin() // H2 Console에서 iframe 허용
//                );
//        return http.build();
//    }
//}


@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/swagger-ui/**",  // Swagger UI 허용
                                "/v3/api-docs/**", // OpenAPI 문서 허용
                                "/h2-console/**",   // H2 Console 허용
                                "/api/members/**", // 멤버 API 허용
                                "/api/categories/**", // 카테고리 API 허용
                                "/api/posts/**", // 게시물 API 허용
                                "/api/comments/**", // Comment API 허용
                                "/api/banners/**" // 배너 API 허용
                        ).permitAll() // 위의 경로들은 모두 허용
                        .anyRequest().authenticated() // 나머지 요청은 인증 필요
                )
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers(
                                "/h2-console/**", // H2 Console CSRF 비활성화
                                "/swagger-ui/**", // Swagger UI CSRF 비활성화
                                "/v3/api-docs/**", // OpenAPI CSRF 비활성화
                                "/api/members/**", // 멤버 API 허용
                                "/api/categories/**", // CSRF 비활성화
                                "/api/posts/**", // 게시물 API 허용
                                "/api/comments/**", // CSRF 비활성화
                                "/api/banners/**" // 배너 API 허용
                        )
                )
                .headers(headers -> headers
                        .frameOptions().sameOrigin() // H2 Console에서 iframe 허용
                );
        return http.build();
    }
}
