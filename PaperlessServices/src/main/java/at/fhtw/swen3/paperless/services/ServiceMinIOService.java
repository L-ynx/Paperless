package at.fhtw.swen3.paperless.services;

import at.fhtw.swen3.paperless.misc.RetrievedObject;

public interface ServiceMinIOService {

    void createBucket(String bucketName);

    RetrievedObject retrieveObject(String fileName);
}
