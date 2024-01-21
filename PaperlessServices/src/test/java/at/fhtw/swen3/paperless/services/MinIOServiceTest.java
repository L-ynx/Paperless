package at.fhtw.swen3.paperless.services;

import at.fhtw.swen3.paperless.misc.RetrievedObject;
import io.minio.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MinIOServiceTest {

    @Mock
    private MinioClient minioClient;

    @InjectMocks
    private MinIOService minIOService;

    private String validBucketName = "testbucket"; // Lowercase and compliant with AWS S3 standards

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        // No need for ReflectionTestUtils since we are directly setting the field
        minIOService = new MinIOService(minioClient);
        // Set the field directly for the purpose of the test
        Field bucketNameField = MinIOService.class.getDeclaredField("bucketName");
        bucketNameField.setAccessible(true);
        bucketNameField.set(minIOService, validBucketName);

        // Mock the Minio client behavior for bucket existence
        when(minioClient.bucketExists(BucketExistsArgs.builder().bucket(validBucketName).build()))
                .thenReturn(false);
        doNothing().when(minioClient).makeBucket(MakeBucketArgs.builder().bucket(validBucketName).build());
    }

    @Test
    void testCreateBucket() throws Exception {
        // Act
        minIOService.createBucket(validBucketName);

        // Assert
        verify(minioClient).bucketExists(BucketExistsArgs.builder().bucket(validBucketName).build());
        verify(minioClient).makeBucket(MakeBucketArgs.builder().bucket(validBucketName).build());
    }
}
