package at.fhtw.swen3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"at.fhtw.swen3.paperless.controller", "at.fhtw.swen3.paperless.services"})
public class PaperlessServicesApplication {
    public static void main(String[] args) {
        SpringApplication.run(PaperlessServicesApplication.class, args);
    }
}