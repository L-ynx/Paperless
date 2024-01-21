package at.fhtw.swen3.paperless.services;

import lombok.extern.slf4j.Slf4j;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
@Slf4j
public class OcrServiceImpl implements OcrService {
    private static final Logger LOGGER = LoggerFactory.getLogger(OcrServiceImpl.class);
    private final String tesseractData;

    @Autowired
    public OcrServiceImpl(@Value("${tesseract.data}") String tessData) {
        this.tesseractData = tessData;
    }


    @Override
    public String executeOCR(File file) {
        var tesseract = new Tesseract();
        tesseract.setDatapath(tesseractData);

        try {
            return tesseract.doOCR(file);
        } catch (TesseractException e) {
            LOGGER.error("Error while executing OCR \n" + e.getMessage());
            return null;
        }
    }
}
