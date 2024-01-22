package at.fhtw.swen3.persistence.repository;

import at.fhtw.swen3.persistence.entity.Document;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class DocumentRepositoryTest {

    @Autowired
    private DocumentRepository documentRepository;

    // Hilfsmethode zum Erstellen eines vollständigen Document-Objekts
    private Document createCompleteDocument() {
        return Document.builder()
                .title("Test Document")
                .content("Lorem ipsum")
                .filesize(12345)
                .originalName("original.txt")
                .checksum("abc123")
                .mimeType("text/plain")
                .storagePath("/path/to/document")
                .owner("user1")
                .createdAt(LocalDateTime.now())
                .docTags(Collections.emptyList()) // Falls benötigt
                .build();
    }

    @Test
    public void testFindAll() {
        // Arrange
        Document document = createCompleteDocument();
        documentRepository.save(document);

        // Act
        List<Document> documents = documentRepository.findAll();

        // Assert
        assertEquals(1, documents.size());
        assertEquals("Test Document", documents.get(0).getTitle());
    }

    @Test
    public void testSave() {
        // Arrange
        Document document = createCompleteDocument();

        // Act
        Document savedDocument = documentRepository.save(document);

        // Assert
        assertEquals("Test Document", savedDocument.getTitle());
    }

    @Test
    public void testFindByID() {
        // Arrange
        Document document = createCompleteDocument();
        Document savedDocument = documentRepository.save(document);

        // Act
        Document foundDocument = documentRepository.findById(savedDocument.getId())
                .orElseThrow(() -> new RuntimeException("Document not found"));

        // Assert
        assertEquals("Test Document", foundDocument.getTitle());
    }
}
