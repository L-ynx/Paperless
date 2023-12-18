package at.fhtw.swen3.paperless.services;

import at.fhtw.swen3.paperless.config.RabbitMQConfig;
import at.fhtw.swen3.paperless.misc.RetrievedObject;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.Path;

@Service
@Slf4j
public class QueueListenerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(QueueListenerService.class);
    private final MinIOService minIOService;
    private final OcrService ocrService;
    private final DocumentService documentService;

    @Autowired
    public QueueListenerService(MinIOService minIOService, OcrService ocrService, DocumentService documentService) {
        this.minIOService = minIOService;
        this.ocrService = ocrService;
        this.documentService = documentService;
    }

    @RabbitListener(queues = RabbitMQConfig.QUEUE)
    public void receiveMessage(String message) {
        LOGGER.info("Message received: {}", message);

        RetrievedObject retrievedObject = minIOService.retrieveObject(message);
        Path path = retrievedObject.path();
        String objectId = retrievedObject.objectId();
        String extractedText = null;

        try {
            extractedText = ocrService.executeOCR(path.toFile());
            documentService.updateContent(extractedText, Long.parseLong(objectId));
        } catch (Exception e) {
            LOGGER.error("Error while executing OCR", e);
        }

        LOGGER.info("Extracted text: {}", extractedText);
    }
}
