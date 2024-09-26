package com.rojojun.kopring.config

import io.swagger.v3.oas.annotations.security.SecurityRequirements
import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.security.SecurityScheme
import org.springdoc.core.models.GroupedOpenApi
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig {
    @Bean
    fun publicApi(): GroupedOpenApi {
        return GroupedOpenApi.builder()
            .group("v1")
            .pathsToMatch("/**") // 원하는 경로 설정
            .build()
    }

    @Bean
    fun customOpenAPI(): OpenAPI {
        val apiKey = SecurityScheme()
            .type(SecurityScheme.Type.APIKEY)
            .`in`(SecurityScheme.In.HEADER)
            .name("Authorization")

        val securityRequirements = SecurityRequirement()
            .addList("Bearer Token")


        return OpenAPI()
            .components(Components()
                .addSecuritySchemes("Bearer Token", apiKey))
            .addSecurityItem(securityRequirements)
            .info(
                Info()
                    .title("Instagram 클론코딩 API")
                    .version("1.0.0")
                    .description("Instagram 클론코딩 프로젝트 API 문서입니다."))
    }
}