package com.dnd12th_4.pickitalki.common.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("PickiTalki API").version("1.0"))
                .components(new Components()
                        // 🔹 Bearer Token 인증 설정
                        .addSecuritySchemes("Bearer Authentication",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT"))
                        // 🔹 Refresh Token 쿠키 인증 설정
                        .addSecuritySchemes("CookieAuth",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.APIKEY)  // 쿠키 기반 인증
                                        .in(SecurityScheme.In.COOKIE)      // 쿠키에서 읽음
                                        .name("refreshToken")              // 쿠키 이름
                        ))
                .addSecurityItem(new SecurityRequirement()
                        .addList("Bearer Authentication")  // Bearer 인증 적용
                        .addList("CookieAuth"));           // RefreshToken 인증 적용
    }
}
