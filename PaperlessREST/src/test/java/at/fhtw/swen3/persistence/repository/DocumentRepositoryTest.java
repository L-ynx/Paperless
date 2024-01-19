package at.fhtw.swen3.persistence.repository;

import at.fhtw.swen3.persistence.entity.Document;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class DocumentRepositoryTest {

    @Autowired
    private DocumentRepository documentRepository;

    @Test
    public void testFindAll() {
        // Arrange
        Document document = Document.builder()
                .title("Test Document")
                .content("Lorem ipsum")
                .build();

        documentRepository.save(document);

        // Act
        List<Document> documents = documentRepository.findAll();

        // Assert
        assertEquals(1, documents.size());
        assertEquals("Test Document", documents.get(0).getTitle());
    }

    @Test
    public void testSave() {
        Document document = Document.builder()
                .title("Test Document")
                .content("Lorem ipsum")
                .build();

        Document savedDocument = documentRepository.save(document);

        assertEquals("Test Document", savedDocument.getTitle());
    }

    @Test
    public void testFindByID() {
        Document document = Document.builder()
                .title("Test Document")
                .content("Lorem ipsum")
                .build();

        Document savedDocument = documentRepository.save(document);

        Document foundDocument = documentRepository.findById(savedDocument.getId()).orElseThrow(() -> new RuntimeException("Document not found"));

        assertEquals("Test Document", foundDocument.getTitle());
    }


}

