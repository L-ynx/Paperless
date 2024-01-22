package at.fhtw.swen3.paperless.config;

import at.fhtw.swen3.paperless.services.ServiceMinIOService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MinIOInit {
    private final ServiceMinIOService serviceMinIOService;

    @Value("${minio.bucket}")
    private String bucketName;

    public MinIOInit(ServiceMinIOService serviceMinIOService) {
        this.serviceMinIOService = serviceMinIOService;
    }

    @PostConstruct
    public void init() {
        serviceMinIOService.createBucket(bucketName);
    }
}
