package za.co.metropolitan.getup.client.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Profile({"dev","tst", "pre"})
public class SwaggerConfig{
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(metaData())
                .host("api-dev.getup.metropolitan.co.za")
                .select()
                .apis(RequestHandlerSelectors.basePackage("za.co.metropolitan.getup.client.controller"))
                .paths(PathSelectors.any())
                .build();
    }
    private ApiInfo metaData(){
        return new ApiInfoBuilder()
                .title("Metropolitan Get Up Client API")
                .description("Integration layer into Metropolitan Getup Client.")
                .termsOfServiceUrl(null)
                .license(null)
                .licenseUrl(null)
                .version("v1")
                .build();
    }
}