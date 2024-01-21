package at.fhtw.swen3.persistence.service;

import io.minio.MinioClient;
import io.minio.errors.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import static org.mockito.Mockito.*;

public class MinIOServiceImplTest {

    private MinIOServiceImpl minIOService;

    @Mock
    private MinioClient minioClient;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        minIOService = new MinIOServiceImpl(minioClient);
    }

    @Test
    void testCreateBucket() throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        // Arrange
        String bucketName = "test-bucket";

        // Mock bucketExists to return false (bucket does not exist)
        when(minioClient.bucketExists(any())).thenReturn(false);

        // Act
        minIOService.createBucket(bucketName);

        // Assert
        verify(minioClient, times(1)).makeBucket(any());
    }

    /*
    @Test
    void testSaveObject() throws Exception {
        // Arrange
        String id = "123";
        MultipartFile file = new MockMultipartFile("test-file.txt", "Hello, World!".getBytes());

        // Act
        minIOService.saveObject(file, id);

        // Assert
        // Verify that minioClient.putObject is called with the expected arguments
        verify(minioClient, times(1)).putObject(any());
    }
    */
}
