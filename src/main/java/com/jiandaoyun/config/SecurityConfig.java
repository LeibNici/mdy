package com.jiandaoyun.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * 安全配置.
 *
 * @author Codex
 *
 * @since 2026/02/28
 */
@Configuration
public class SecurityConfig {

    /**
     * 构建安全过滤链.
     *
     * @param http Spring Security HTTP 配置对象
     *
     * @return 安全过滤链
     *
     * @throws Exception 安全配置失败时抛出
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth.anyRequest().permitAll())
            .httpBasic(Customizer.withDefaults())
            .build();
    }
}
