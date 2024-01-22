package at.fhtw.swen3.paperless.services;

import at.fhtw.swen3.paperless.entity.Document;
import at.fhtw.swen3.paperless.misc.RetrievedObject;
import co.elastic.clients.elasticsearch._types.Result;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.File;
import java.nio.file.Paths;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class QueueListenerServiceTest {

    @Mock
    private ServiceMinIOService serviceMinIOService;
    @Mock
    private OcrService ocrService;
    @Mock
    private SearchIndexService searchIndexService;
    @Mock
    private ServiceDocumentService serviceDocumentService;

    @InjectMocks
    private QueueListenerServiceImpl queueListenerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testReceiveMessage() throws Exception {
        // Arrange
        String message = "objectId";
        String extractedText = "Extracted OCR text";
        String title = "Document Title";
        Long documentId = 1L;

        RetrievedObject retrievedObject = new RetrievedObject(Paths.get("dummy/path"), message);
        Document indexedDocument = new Document();
        indexedDocument.setId(documentId);
        indexedDocument.setContent(extractedText);
        indexedDocument.setTitle(title);

        when(serviceMinIOService.retrieveObject(message)).thenReturn(retrievedObject);
        when(ocrService.executeOCR(any(File.class))).thenReturn(extractedText);
        when(serviceDocumentService.updateContent(eq(extractedText), eq(documentId))).thenReturn(title);
        when(searchIndexService.indexDocument(any(Document.class))).thenReturn(Result.Created);
        when(searchIndexService.getDocumentById(anyInt())).thenReturn(Optional.of(indexedDocument));

        // Act
        queueListenerService.receiveMessage(message);

        // Assert
        verify(serviceMinIOService).retrieveObject(message);
    }

}