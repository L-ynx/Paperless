package at.fhtw.swen3.paperless.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FullyQualifiedAnnotationBeanNameGenerator;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ConfigurationProperties(prefix = "app")
@ComponentScan(basePackages = {"at.fhtw.swen3.paperless", "at.fhtw.swen3.persistence"},
        nameGenerator = FullyQualifiedAnnotationBeanNameGenerator.class)
@EntityScan("at.fhtw.swen3.persistence.entity")
@EnableJpaRepositories(basePackages = {"at.fhtw.swen3.persistence.repository"})
public class SpringDocConfiguration {

    @Bean(name = "at.fhtw.swen3.paperless.config.SpringDocConfiguration.apiInfo")
    OpenAPI apiInfo() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("Paperless Rest Server")
                                .description("No description provided (generated by Openapi Generator https://github.com/openapitools/openapi-generator)")
                                .version("1.0")
                )
                ;
    }
}