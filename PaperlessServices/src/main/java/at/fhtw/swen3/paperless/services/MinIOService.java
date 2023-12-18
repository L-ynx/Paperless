package at.fhtw.swen3.paperless.services;

import io.minio.BucketExistsArgs;
import io.minio.GetObjectArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@Slf4j
@Service
public class MinIOService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MinIOService.class);
    private final MinioClient minioClient;

    @Value("${minio.bucket}")
    private String bucketName;

    @Autowired
    public MinIOService(MinioClient minioClient) {
        this.minioClient = minioClient;
    }

    public void createBucket(String bucketName) {
        try {
            boolean found =
                    minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
            if (!found) {
                // Create a new bucket
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
                LOGGER.info("Bucket " + bucketName + " created successfully.");
            } else {
                LOGGER.info("Bucket " + bucketName + " already exists.");
            }
        } catch (Exception e) {
            LOGGER.warn("Bucket " + bucketName + " not created.");
        }
    }

    public Path retrieveObject(String fileName) {
        try (InputStream stream = minioClient.getObject(
                GetObjectArgs.builder()
                        .bucket(bucketName)
                        .object(fileName)
                        .build())) {

            Path path = Path.of("/" + fileName);
            Files.copy(stream, path, StandardCopyOption.REPLACE_EXISTING);

            LOGGER.info("Object " + fileName + " retrieved successfully from MinIO.");

            return path;
        } catch (Exception e) {
            LOGGER.warn("Object " + fileName + " not retrieved.");
            throw new RuntimeException("Could not retrieve object from MinIO", e);
        }
    }
}
