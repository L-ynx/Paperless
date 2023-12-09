package at.fhtw.swen3.paperless.services;

import at.fhtw.swen3.paperless.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.messaging.handler.annotation.Header;

@Service
public class OcrServiceImpl implements OcrService{
    private final String tesseractData;

    @Autowired
    public OcrServiceImpl( @Value("${tesseract.data}") String tessData) {
        this.tesseractData = tessData;
    }


    @Override
    public void processMessage(String message,  @Header(RabbitMQConfig.DOCUMENT_STORAGE_PATH_PROPERTY_NAME) String storagePath) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
