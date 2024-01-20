package at.fhtw.swen3.paperless.services;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import at.fhtw.swen3.paperless.entity.Document;
import at.fhtw.swen3.paperless.misc.RetrievedObject;
import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.*;
import co.elastic.clients.elasticsearch._types.Result;
import co.elastic.clients.util.ObjectBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import co.elastic.clients.elasticsearch.core.GetRequest;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.function.Function;

@ExtendWith(MockitoExtension.class)
class ElasticSearchServiceTest {

    @Mock
    private ElasticsearchClient esClient;

    @Mock
    private ElasticSearchService elasticSearchService;
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

    /*
    @Test
    void testGetDocumentByIdFound() throws IOException {
        // Arrange
        int id = 1;
        Document expectedDocument = new Document();
        expectedDocument.setId(id);
        GetResponse<Document> response = mock(GetResponse.class);

        when(response.found()).thenReturn(true);
        when(response.source()).thenReturn(expectedDocument);

        // Assuming your esClient.get method expects a Function for the request and a Class for the document type
        when(esClient.get(any(Function.class), eq(Document.class))).thenReturn(response);

        // Act
        Optional<Document> result = elasticSearchService.getDocumentById(id);

        // Assert
        assertTrue(result.isPresent(), "Document should be found");
        assertEquals(expectedDocument, result.get(), "The returned document should match the expected document");

        // Verify that esClient.get was called with correct parameters
        verify(esClient).get(any(Function.class), eq(Document.class));
    }
    */

    @Test
    void testGetDocumentByIdNotFound() throws IOException {
        // Arrange
        int id = 1;
        GetResponse<Document> response = Mockito.mock(GetResponse.class);
        lenient().when(response.found()).thenReturn(false); // Make this stubbing lenient
        lenient().when(esClient.get((GetRequest) any(), eq(Document.class))).thenReturn(response); // Make this stubbing lenient

        // Act
        Optional<Document> result = elasticSearchService.getDocumentById(id);

        // Assert
        assertEquals(Optional.empty(), result);
    }
}