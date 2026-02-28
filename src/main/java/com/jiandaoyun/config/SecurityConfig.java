package com.jiandaoyun.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * 安全配置类.
 *
 * @author chenming
 *
 * @since 2026/02/28
 */
@Configuration
public class SecurityConfig {

    /**
     * 执行安全FilterChain操作.
     *
     * @param http .
     * @return 处理结果.
     * @throws Exception Exception 异常信息.
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
