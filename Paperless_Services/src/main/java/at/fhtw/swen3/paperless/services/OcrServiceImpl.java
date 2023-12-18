package at.fhtw.swen3.paperless.services;

import lombok.extern.slf4j.Slf4j;
import net.sourceforge.tess4j.Tesseract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
@Slf4j
public class OcrServiceImpl implements OcrService {
    private final String tesseractData;

    @Autowired
    public OcrServiceImpl(@Value("${tesseract.data}") String tessData) {
        this.tesseractData = tessData;
    }


    @Override
    public String executeOCR(File file) throws Exception {
        var tesseract = new Tesseract();
        tesseract.setDatapath(tesseractData);

        return tesseract.doOCR(file);
    }
}
