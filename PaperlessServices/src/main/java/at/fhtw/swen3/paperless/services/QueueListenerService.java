package at.fhtw.swen3.paperless.services;

import at.fhtw.swen3.paperless.config.RabbitMQConfig;
import at.fhtw.swen3.paperless.entity.Document;
import at.fhtw.swen3.paperless.misc.RetrievedObject;
import at.fhtw.swen3.paperless.services.SearchIndexService;
import co.elastic.clients.elasticsearch._types.Result;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.util.Optional;

@Service
@Slf4j
public class QueueListenerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(QueueListenerService.class);
    private final MinIOService minIOService;
    private final OcrService ocrService;
    private final SearchIndexService searchIndexService;
    private final DocumentService documentService;

    @Autowired
    public QueueListenerService(MinIOService minIOService, OcrService ocrService, SearchIndexService searchIndexService, DocumentService documentService) {
        this.minIOService = minIOService;
        this.ocrService = ocrService;
        this.searchIndexService = searchIndexService;
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

            // Assuming the document entity or similar object to index
            Document document = new Document();
            document.setId(Long.parseLong(objectId));
            document.setContent(extractedText);

            // Index the document
            Result indexResult = searchIndexService.indexDocument(document);
            LOGGER.info("Document indexed successfully: {}", objectId);

            // Now verify if the document has been stored in Elasticsearch
            Optional<Document> indexedDocumentOpt = searchIndexService.getDocumentById(Integer.parseInt(objectId));
            if (indexedDocumentOpt.isPresent()) {
                Document indexedDocument = indexedDocumentOpt.get();
                // Perform any additional checks if necessary, for example, on the content
                if (extractedText.equals(indexedDocument.getContent())) {
                    LOGGER.info("Verified that the document content is stored correctly in Elasticsearch for ID: {}", objectId);
                } else {
                    LOGGER.warn("The document content in Elasticsearch may differ for ID: {}", objectId);
                }
            } else {
                LOGGER.error("Document not found in Elasticsearch for ID: {}", objectId);
            }

        } catch (Exception e) {
            LOGGER.error("Error while executing OCR or indexing document", e);
        }

        LOGGER.info("Extracted text: {}", extractedText);
    }
}
