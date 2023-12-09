package at.fhtw.swen3.persistence.service;

import io.minio.MinioClient;
import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MinIOServiceImpl implements MinIOService{
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
            } else {
                System.out.println("Bucket " + bucketName + " already exists.");
            }
    } catch (Exception e) {
        System.out.println("Bucket could not be created: " + e);
    }
    }
}
