package com.sparta.domain.admin.config;

import com.sparta.domain.admin.jwt.JwtAuthenticationFilter;
import com.sparta.domain.admin.jwt.JwtAuthorizationFilter;
import com.sparta.domain.admin.jwt.JwtUtil;
import com.sparta.domain.admin.security.AdminDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig {
    private final JwtUtil jwtUtil;
    private final AdminDetailsServiceImpl adminDetailsService;
    private final AuthenticationConfiguration authenticationConfiguration;

    public WebSecurityConfig(JwtUtil jwtUtil, AdminDetailsServiceImpl adminDetailsService, AuthenticationConfiguration authenticationConfiguration) {
        this.jwtUtil = jwtUtil;
        this.adminDetailsService = adminDetailsService;
        this.authenticationConfiguration = authenticationConfiguration;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() throws Exception {
        JwtAuthenticationFilter filter = new JwtAuthenticationFilter(jwtUtil);
        filter.setAuthenticationManager(authenticationManager(authenticationConfiguration));
        return filter;
    }

    @Bean
    public JwtAuthorizationFilter jwtAuthorizationFilter() {
        return new JwtAuthorizationFilter(jwtUtil, adminDetailsService);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // 1. CSRF 설정 비활성화
        http.csrf((csrf) -> csrf.disable());

        // 2. JWT 사용 선언
        http.sessionManagement((sessionManagement) ->
                sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        );

        // 3. HTTP 요청 인증 구성 설정
        http.authorizeHttpRequests((authorizeHttpRequests) ->
                authorizeHttpRequests
                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll() // resources 요청 허용
                        .requestMatchers("/api/admin/**").permitAll() // /api/admin/ 요청 허용 -> ✅ hasRole() 사용 여부 확인
                        .anyRequest().authenticated() // 이외 요청의 경우, 일괄 인증 처리 필요
        );

        // 4. HTTP 폼 로그인 설정
        http.formLogin((formLogin) ->
                formLogin
                        .loginPage("/api/admin/login-page").permitAll()
        );

        // 5. 필터 순서 설정
        http.addFilterBefore(jwtAuthorizationFilter(), JwtAuthenticationFilter.class);
        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        // 6. 접근 불가 페이지 설정
        http.exceptionHandling((exceptionHandling) ->
                exceptionHandling
                        .accessDeniedPage("/forbidden.html")
        );
        return http.build();
    }
}