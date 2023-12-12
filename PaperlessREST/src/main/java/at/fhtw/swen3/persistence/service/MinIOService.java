package at.fhtw.swen3.persistence.service;

public interface MinIOService {
    void createBucket(String bucketName);
    void saveObject();
}
