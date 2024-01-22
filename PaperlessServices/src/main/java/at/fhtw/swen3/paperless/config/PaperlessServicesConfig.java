package at.fhtw.swen3.paperless.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "at.fhtw.swen3.paperless.repository")
public class PaperlessServicesConfig {
}
