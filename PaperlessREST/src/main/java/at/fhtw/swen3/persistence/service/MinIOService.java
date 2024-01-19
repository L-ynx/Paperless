package at.fhtw.swen3.persistence.service;

import org.springframework.web.multipart.MultipartFile;

public interface MinIOService {
    void createBucket(String bucketName);

    void saveObject(MultipartFile file, String id);

    void deleteObject(String id);
}
