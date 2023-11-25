package at.fhtw.swen3.persistence.mapper;

import at.fhtw.swen3.persistence.entity.Correspondent;
import at.fhtw.swen3.persistence.entity.DocTag;
import at.fhtw.swen3.persistence.entity.Document;
import at.fhtw.swen3.persistence.entity.DocumentType;
import at.fhtw.swen3.persistence.service.dto.DocumentDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class DatabaseMapperTest {

    private final DatabaseMapper mapper;

    @Autowired
    public DatabaseMapperTest(DatabaseMapper mapper) {
        this.mapper = mapper;
    }

    @Test
    public void testToDTO() {
        Document document = Document.builder()
                .id(1)
                .title("Sample Document")
                .content("Lorem ipsum")
                .createdAt(LocalDateTime.now())
                .correspondent(new Correspondent())
                .documentType(new DocumentType())
                .docTags(Arrays.asList(new DocTag(), new DocTag()))
                .build();

        DocumentDTO documentDTO = mapper.toDTO(document);

        assertEquals(document.getId(), documentDTO.getId());
        assertEquals(document.getTitle(), documentDTO.getTitle());
        assertEquals(document.getContent(), documentDTO.getContent());
        assertEquals(document.getDocTags().size(), documentDTO.getDocTags().size());
    }

    @Test
    public void testToEntity() {
        DocumentDTO documentDTO = DocumentDTO.builder()
                .id(1)
                .title("Sample Document")
                .content("Lorem ipsum")
                .createdAt(LocalDateTime.now())
                .correspondent(new Correspondent())
                .documentType(new DocumentType())
                .docTags(Arrays.asList(new DocTag(), new DocTag()))
                .build();

        Document document = mapper.toEntity(documentDTO);

        assertEquals(documentDTO.getId(), document.getId());
        assertEquals(documentDTO.getTitle(), document.getTitle());
        assertEquals(documentDTO.getContent(), document.getContent());
        assertEquals(documentDTO.getDocTags().size(), document.getDocTags().size());
    }

    @Test
    public void testToDTOs() {
        List<Document> documents = Arrays.asList(
                Document.builder().id(1).title("Document 1").build(),
                Document.builder().id(2).title("Document 2").build()
        );

        List<DocumentDTO> documentDTOs = mapper.toDTOs(documents);

        assertEquals(documents.size(), documentDTOs.size());
        assertEquals(documents.get(0).getId(), documentDTOs.get(0).getId());
        assertEquals(documents.get(1).getId(), documentDTOs.get(1).getId());
        assertEquals(documents.get(0).getTitle(), documentDTOs.get(0).getTitle());
        assertEquals(documents.get(1).getTitle(), documentDTOs.get(1).getTitle());
    }
}
