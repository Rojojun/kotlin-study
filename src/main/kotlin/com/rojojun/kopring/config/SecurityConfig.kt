package com.rojojun.kopring.config

import com.rojojun.kopring.security.CustomJwtFilter
import com.rojojun.kopring.security.JwtUtil
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
class SecurityConfig(
    private val jwtUtil: JwtUtil,
    private val customJwtFilter: CustomJwtFilter
) {

    @Bean
    fun webSecurityCustomizer(): WebSecurityCustomizer = WebSecurityCustomizer { webSecurity ->
        webSecurity.ignoring().requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html", "/health")
    }

    @Bean
    fun defaultSecurityFilterChain(http: HttpSecurity): SecurityFilterChain {
        return http.csrf{ it.disable() }
            .formLogin{ formLogin -> formLogin.disable() }
            .authorizeHttpRequests {
                request -> request.requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html", "health").permitAll()
                    .anyRequest().authenticated()
            }
            .addFilterBefore(customJwtFilter, UsernamePasswordAuthenticationFilter::class.java)
            .build()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder();
}