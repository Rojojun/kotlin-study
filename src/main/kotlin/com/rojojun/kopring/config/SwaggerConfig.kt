package com.rojojun.kopring.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket

@Configuration
class SwaggerConfig {
    @Bean
    fun swaggerApi(): Docket = Docket(DocumentationType.OAS_30)
        .consumes(getConsumeContentTypes())
        .produces(getProduceContentTypes())
        .apiInfo(swaggerInfo())
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.colabear754.swagger_example.controllers"))
        .paths(PathSelectors.any())
        .build()
        .useDefaultResponseMessages(false)

    private fun swaggerInfo() = ApiInfoBuilder()
        .title("Instagram 클론코딩 Swagger API")
        .description("Instagram application 에 대한 Swagger API")
        .version("1.0.0")
        .build()

    private fun getConsumeContentTypes(): Set<String> {
        val comsumes = HashSet<String>()
        comsumes.add("multipart/form-data")
        return comsumes
    }

    private fun getProduceContentTypes(): Set<String> {
        val produces = HashSet<String>()
        produces.add("application/json;charset=UTF-8")
        return produces
    }
}