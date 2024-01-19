package at.fhtw.swen3.paperless.services;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import at.fhtw.swen3.paperless.entity.Document;
import at.fhtw.swen3.paperless.misc.RetrievedObject;
import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.*;
import co.elastic.clients.elasticsearch._types.Result;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

class ElasticSearchServiceTest {

    @Mock
    private MinIOService mockMinIOService;
    @Mock
    private OcrService mockOcrService;
    @Mock
    private SearchIndexService mockSearchIndexService;
    @Mock
    private DocumentService mockDocumentService;

    private QueueListenerService queueListenerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        queueListenerService = new QueueListenerService(mockMinIOService, mockOcrService, mockSearchIndexService, mockDocumentService);
    }

    @Test
    void testReceiveMessageIndexResult() throws Exception {
        // Arrange
        String message = "testMessage";
        RetrievedObject retrievedObject = new RetrievedObject(Path.of("dummyPath"), "1");
        when(mockMinIOService.retrieveObject(message)).thenReturn(retrievedObject);
        when(mockOcrService.executeOCR(any())).thenReturn("Extracted Text");
        when(mockDocumentService.updateContent(anyString(), anyLong())).thenReturn("Test Title");

        when(mockSearchIndexService.indexDocument(any(Document.class))).thenReturn(Result.Created); // Assuming Result is the correct return type

        // Act
        queueListenerService.receiveMessage(message);

        // Assert
        verify(mockSearchIndexService).indexDocument(any(Document.class));
        // No need to verify mockIndexResponse.result() since it's part of the stubbing
    }
}