package at.fhtw.swen3.paperless.controller;

import at.fhtw.swen3.paperless.services.dto.GetDocument200Response;
import at.fhtw.swen3.persistence.entity.Document;
import at.fhtw.swen3.persistence.mapper.DatabaseMapper;
import at.fhtw.swen3.persistence.service.*;
import at.fhtw.swen3.persistence.service.dto.DocumentDTO;
import at.fhtw.swen3.persistence.service.messageQueue.MessageQueueService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;

import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DocumentsApiControllerTest {

    @Mock
    private DocumentService documentService;
    @Mock
    private DocumentTypeService documentTypeService;
    @Mock
    private CorrespondentService correspondentService;
    @Mock
    private DocTagService docTagService;
    @Mock
    private MinIOService minIOService;
    @Mock
    private MessageQueueService messageQueueService;
    @Mock
    private DatabaseMapper mapper;
    @Mock
    private NativeWebRequest request;

    @InjectMocks
    private DocumentsApiController controller;

    // Add setup for common mocks if necessary

    @BeforeEach
    void setUp() {
        // Initialize common mock behavior, if applicable
    }

    @Test
    void testGetDocument() {
        // Arrange
        int documentId = 1;
        DocumentDTO mockDocument = new DocumentDTO();
        mockDocument.setId(documentId);
        mockDocument.setTitle("Sample Document");
        mockDocument.setContent("This is the content of the document.");
        when(documentService.findById(documentId)).thenReturn(mockDocument);

        // Act
        ResponseEntity<GetDocument200Response> response = controller.getDocument(documentId, 1, true);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(documentId, response.getBody().getId());
        assertEquals("Sample Document", response.getBody().getTitle());
        assertEquals("This is the content of the document.", response.getBody().getContent());
    }

    @Test
    void testDeleteDocument() {
        // Arrange
        int documentId = 1;

        doNothing().when(documentService).deleteDocument(documentId);
        doNothing().when(minIOService).deleteObject(String.valueOf(documentId));

        // Act
        ResponseEntity<Void> response = controller.deleteDocument(documentId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(documentService).deleteDocument(documentId);
        verify(minIOService).deleteObject(String.valueOf(documentId));
    }

    /*
    @Test
    void testUploadDocument() {
        // Arrange
        MockMultipartFile mockFile = new MockMultipartFile("document", "test.txt", "text/plain", "test content".getBytes());
        String title = "New Document";
        OffsetDateTime created = OffsetDateTime.now(); // Assume current date and time
        Integer documentType = 1; // Assume type ID
        List<Integer> tags = Arrays.asList(1, 2); // Assume tag IDs
        Integer correspondent = 1; // Assume correspondent ID

        Document mockDocument = new Document();
        mockDocument.setTitle(title);
        // Set other properties...
        // Assuming that the saved document will be returned

        // Lenient stubbing for saveDocument call with any argument, including null
        lenient().when(documentService.saveDocument(any(Document.class))).thenReturn(mockDocument);
        lenient().when(documentService.saveDocument(null)).thenReturn(mockDocument);

        doNothing().when(minIOService).saveObject(any(MultipartFile.class), anyString());
        doNothing().when(messageQueueService).processMessage(anyString());

        // Act
        ResponseEntity<String> response = controller.uploadDocument(title, created, documentType, tags, correspondent, Arrays.asList(mockFile));

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(documentService, atLeastOnce()).saveDocument(any(Document.class));
        verify(minIOService).saveObject(any(MultipartFile.class), anyString());
        verify(messageQueueService).processMessage(anyString());
    }
    */

}