package at.fhtw.swen3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("at.fhtw.swen3.paperless.entity")
public class PaperlessServicesApplication {
    public static void main(String[] args) {
        SpringApplication.run(PaperlessServicesApplication.class, args);
    }
}