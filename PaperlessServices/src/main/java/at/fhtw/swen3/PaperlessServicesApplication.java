package at.fhtw.swen3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"at.fhtw.swen3.paperless.services", "at.fhtw.swen3.paperless.config", "at.fhtw.swen3.paperless.repository"})
public class PaperlessServicesApplication {
    public static void main(String[] args) {
        SpringApplication.run(PaperlessServicesApplication.class, args);
    }
}