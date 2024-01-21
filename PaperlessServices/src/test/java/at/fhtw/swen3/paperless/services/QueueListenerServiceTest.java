package at.fhtw.swen3.paperless.services;

import at.fhtw.swen3.paperless.entity.Document;
import at.fhtw.swen3.paperless.misc.RetrievedObject;
import at.fhtw.swen3.paperless.services.*;
import co.elastic.clients.elasticsearch._types.Result;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class QueueListenerServiceTest {

    @Mock
    private MinIOService minIOService;
    @Mock
    private OcrService ocrService;
    @Mock
    private SearchIndexService searchIndexService;
    @Mock
    private DocumentService documentService;

    @InjectMocks
    private QueueListenerService queueListenerService;

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

        when(minIOService.retrieveObject(message)).thenReturn(retrievedObject);
        when(ocrService.executeOCR(any(File.class))).thenReturn(extractedText);
        when(documentService.updateContent(eq(extractedText), eq(documentId))).thenReturn(title);
        when(searchIndexService.indexDocument(any(Document.class))).thenReturn(Result.Created);
        when(searchIndexService.getDocumentById(anyInt())).thenReturn(Optional.of(indexedDocument));

        // Act
        queueListenerService.receiveMessage(message);

        // Assert
        verify(minIOService).retrieveObject(message);
    }

}