package at.fhtw.swen3.paperless.config;


import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MinIOConfiguration {

    @Value("${minio.user}")
    private String user;
    @Value("${minio.pass}")
    private String password;
    @Value("${minio.url}")
    private String url;

    @Bean
    public MinioClient minioClient() {
        try{

            return MinioClient.builder()
                    .endpoint(url)
                    .credentials(user, password)
                    .build();
        } catch (Exception e) {
            throw new RuntimeException("Could not initialize MinIO client", e);
        }
    }
}
