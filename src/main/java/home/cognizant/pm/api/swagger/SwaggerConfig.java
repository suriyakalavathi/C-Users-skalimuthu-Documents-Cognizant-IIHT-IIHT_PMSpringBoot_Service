package home.cognizant.pm.api.swagger;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

// Swagger UI @ http://localhost:8080/swagger-ui.html
// Swagger Doc @ http://localhost:8080/v2/api-docs

@Slf4j

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private ApiInfo apiEndPointsInfo() {
        return new ApiInfoBuilder().title("REST API")
                .description("References")
                .contact(new Contact("Program Manager", "http://localhost:4200/projectmanager", "suriyakalavathi.kalimuthu@cognizant.com"))
                .license("Apache 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
                .version("1.0-SNAPSHOT")
                .build();
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("home.cognizant.pm.api"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiEndPointsInfo());
    }

}
