package at.fhtw.swen3.paperless.services;

import at.fhtw.swen3.paperless.repository.DocumentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class ServiceDocumentServiceTest {

    @Mock
    private DocumentRepository documentRepository;

    @InjectMocks
    private ServiceDocumentServiceImpl documentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testUpdateContent() {
        // Arrange
        String content = "Updated content";
        long id = 1L;
        String expectedTitle = "Document Title";

        // Stub the repository methods
        doNothing().when(documentRepository).updateContent(eq(content), eq(id));
        when(documentRepository.findTitleById(eq(id))).thenReturn(expectedTitle);

        // Act
        String actualTitle = documentService.updateContent(content, id);

        // Assert
        assertEquals(expectedTitle, actualTitle, "The title returned should match the expected title.");

        // Verify that the repository methods are called correctly
        verify(documentRepository).updateContent(eq(content), eq(id));
        verify(documentRepository).findTitleById(eq(id));
    }
}
