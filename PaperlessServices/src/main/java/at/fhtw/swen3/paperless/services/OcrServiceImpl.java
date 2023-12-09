package at.fhtw.swen3.paperless.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class OcrServiceImpl implements OcrService{

    @Value("${tesseract.data}")
    private String TESSERACT_DATA;


    @Override
    public void processMessage(String message, String storagePath) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
