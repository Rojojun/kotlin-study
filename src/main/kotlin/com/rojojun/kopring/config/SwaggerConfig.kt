package com.rojojun.kopring.config

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
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
        return OpenAPI()
            .info(
                Info()
                    .title("Instagram 클론코딩 API")
                    .version("1.0.0")
                    .description("Instagram 클론코딩 프로젝트 API 문서입니다.")
            )
    }
}