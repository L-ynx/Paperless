package at.fhtw.swen3.paperless.config;

import at.fhtw.swen3.paperless.services.MinIOService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MinIOInit {
    private final MinIOService minIOService;

    @Value("${minio.bucket}")
    private String bucketName;

    public MinIOInit(MinIOService minIOService) {
        this.minIOService = minIOService;
    }

    @PostConstruct
    public void init() {
        minIOService.createBucket(bucketName);
    }
}
