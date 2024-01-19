package at.fhtw.swen3.persistence.mapper;

import at.fhtw.swen3.persistence.entity.*;
import at.fhtw.swen3.persistence.service.dto.*;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DatabaseMapperTest {

    private final DatabaseMapper mapper = Mappers.getMapper(DatabaseMapper.class);

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

    @Test
    public void testDocTagMapping() {
        DocTag docTag = new DocTag();
        docTag.setId(1);
        docTag.setSlug("tag-slug");
        docTag.setName("Tag Name");
        docTag.setColor("Tag Color");
        docTag.setMatch("Tag Match");
        docTag.setMatchingAlgorithm(12312313);
        docTag.setInsensitive(true);
        docTag.setInboxTag(true);
        docTag.setDocumentCount(5L);

        DocTagDTO docTagDTO = mapper.toDTO(docTag);

        assertEquals(docTag.getId(), docTagDTO.getId());
        assertEquals(docTag.getSlug(), docTagDTO.getSlug());
        assertEquals(docTag.getName(), docTagDTO.getName());
        assertEquals(docTag.getColor(), docTagDTO.getColor());
        assertEquals(docTag.getMatch(), docTagDTO.getMatch());
        assertEquals(docTag.getMatchingAlgorithm(), docTagDTO.getMatchingAlgorithm());
        assertEquals(docTag.isInsensitive(), docTagDTO.isInsensitive());
        assertEquals(docTag.isInboxTag(), docTagDTO.isInboxTag());
        assertEquals(docTag.getDocumentCount(), docTagDTO.getDocumentCount());
    }

    @Test
    public void testDocumentTypeMapping() {
        DocumentType documentType = new DocumentType();
        documentType.setId(1);
        documentType.setName("Type Name");
        documentType.setMatchingAlgorithm(10239132);
        documentType.setInsensitive(true);

        DocumentTypeDTO documentTypeDTO = mapper.toDTO(documentType);

        assertEquals(documentType.getId(), documentTypeDTO.getId());
        assertEquals(documentType.getName(), documentTypeDTO.getName());
        assertEquals(documentType.getMatchingAlgorithm(), documentTypeDTO.getMatchingAlgorithm());
        assertEquals(documentType.isInsensitive(), documentTypeDTO.isInsensitive());
    }

    @Test
    public void testStatisticsMapping() {
        Statistics statistics = new Statistics();
        statistics.setId(1);
        statistics.setDocumentsTotalCount(100L);
        statistics.setDocumentsInboxCount(30L);
        statistics.setInboxTagCount(5L);
        statistics.setCharacterCount(500L);

        StatisticsDTO statisticsDTO = mapper.toDTO(statistics);

        assertEquals(statistics.getId(), statisticsDTO.getId());
        assertEquals(statistics.getDocumentsTotalCount(), statisticsDTO.getDocumentsTotalCount());
        assertEquals(statistics.getDocumentsInboxCount(), statisticsDTO.getDocumentsInboxCount());
        assertEquals(statistics.getInboxTagCount(), statisticsDTO.getInboxTagCount());
        assertEquals(statistics.getCharacterCount(), statisticsDTO.getCharacterCount());
    }

    @Test
    public void testUserInfoMapping() {
        UserInfo userInfo = new UserInfo();
        userInfo.setId(1);
        userInfo.setUsername("user123");
        userInfo.setPassword("password123");

        UserInfoDTO userInfoDTO = mapper.toDTO(userInfo);

        assertEquals(userInfo.getId(), userInfoDTO.getId());
        assertEquals(userInfo.getUsername(), userInfoDTO.getUsername());
        assertEquals(userInfo.getPassword(), userInfoDTO.getPassword());
    }
}
