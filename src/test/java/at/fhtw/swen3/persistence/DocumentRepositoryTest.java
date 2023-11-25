package at.fhtw.swen3.persistence;

import at.fhtw.swen3.persistence.entity.Document;
import at.fhtw.swen3.persistence.repository.DocumentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(properties = "spring.config.name=application-test")
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
}

