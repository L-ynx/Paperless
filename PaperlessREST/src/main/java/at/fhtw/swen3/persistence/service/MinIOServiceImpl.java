package at.fhtw.swen3.persistence.service;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class MinIOServiceImpl implements MinIOService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MinIOServiceImpl.class);
    private final MinioClient minioClient;

    @Value("${minio.bucket}")
    private String bucketName;

    @Autowired
    public MinIOServiceImpl(MinioClient minioClient) {
        this.minioClient = minioClient;
    }

    @Override
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

    @Override
    public void saveObject(MultipartFile file, String id) {
        Map<String, String> docID = new HashMap<>();
        docID.put("id", id);
        try (InputStream is = file.getInputStream()) {
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(bucketName)
                            .object(file.getOriginalFilename())
                            .userMetadata(docID)
                            .stream(is, file.getSize(), -1)
                            .contentType(file.getContentType())
                            .build()
            );
            LOGGER.info("Object " + file.getOriginalFilename() + " saved successfully to MinIO.");
        } catch (Exception e) {
            LOGGER.warn("Object " + file.getOriginalFilename() + " not saved.");
        }
    }
}
