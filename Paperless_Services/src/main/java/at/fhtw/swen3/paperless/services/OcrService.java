package at.fhtw.swen3.paperless.services;

import java.io.File;

public interface OcrService {
    String executeOCR(File file) throws Exception;
}
