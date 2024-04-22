package com.spring.boilerplate.global.config.swagger;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.customizers.GlobalOpenApiCustomizer;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * [Swagger]
 * Swagger Config 설정
 * @since 2024-04-16
 * @author Jayden (최성민)
 * @version 1.0
 */
@Configuration
public class SwaggerConfig {

    private final String ACCESS_TOKEN = "AccessToken";
    private final String REFRESH_TOKEN = "RefreshToken";

    @Bean
    public OpenAPI openAPI() {
        Server localServer = new Server();
        localServer.setUrl("http://localhost:8080");

        return new OpenAPI()
                .servers(List.of(localServer))
                .addSecurityItem(securityRequirement)
                .components(componentsInfo())
                .info(apiInfo());
    }

    @Bean
    public GroupedOpenApi allGroup() {
        return GroupedOpenApi.builder()
                .group("All")
                .pathsToMatch("/**")
                .build();
    }

    @Bean
    public GroupedOpenApi mainGroup() {
        return GroupedOpenApi.builder()
                .group("Main")
                .pathsToMatch("/auth/**", "/deposit/**", "/withdraw/**", "/info/**", "/games/**", "/gnb/**", "/inquiry/**", "/message/**")
                .build();
    }

    SecurityRequirement securityRequirement = new SecurityRequirement().addList(ACCESS_TOKEN).addList(REFRESH_TOKEN);

    private Components componentsInfo() {
        return new Components()
                .addSecuritySchemes(ACCESS_TOKEN, securityRequirementInfo("Authorization"))
                .addSecuritySchemes(REFRESH_TOKEN, securityRequirementInfo("Authorization-Refresh"));
    }

    private SecurityScheme securityRequirementInfo(String headerType) {
        return new SecurityScheme()
                .type(SecurityScheme.Type.APIKEY)
                .scheme("BEARER")
                .bearerFormat("JWT")
                .name(headerType)
                .in(SecurityScheme.In.HEADER);
    }

    private Info apiInfo() {
        return new Info()
                .title("Spring Boilerplate")
                .description("스프링 보일러 플레이트 스웨거 설정 입니다.")
                .version("1.0.0");
    }

    // 공통으로 사용되는 response 설정
    @Bean
    public GlobalOpenApiCustomizer customerGlobalHeaderOpenApiCustomizer() {
        return openApi -> {
            openApi.getPaths().values().forEach(pathItem -> pathItem.readOperations().forEach(operation -> {
                ApiResponses apiResponses = operation.getResponses();
                apiResponses.addApiResponse("200", createApiResponse(apiResponses.get("200").getDescription(), apiResponses.get("200").getContent()));
                apiResponses.addApiResponse("400", createApiResponse("Bad Request", null));
                apiResponses.addApiResponse("401", createApiResponse("Token Error", null));
                apiResponses.addApiResponse("404", createApiResponse("Not Found", null));
                apiResponses.addApiResponse("500", createApiResponse("Server Error", null));
            }));
        };
    }

    public ApiResponse createApiResponse(String message, Content content){
        return new ApiResponse().description(message).content(content);
    }
}
