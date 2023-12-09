package at.fhtw.swen3.paperless.services;

public interface OcrService {
    void processMessage(String message, String storagePath);
}
