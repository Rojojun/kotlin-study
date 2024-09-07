package com.rojojun.kopring.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
class SecurityConfig {

    @Bean
    public fun webSecurityCustomizer(): WebSecurityCustomizer = WebSecurityCustomizer { webSecurity ->
        webSecurity.ignoring().requestMatchers("/swagger-ui/**", "/swagger-ui.html", "/health")
    }

    @Bean
    public fun

    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder();
}