package at.fhtw.swen3.persistence.service;

import at.fhtw.swen3.persistence.entity.DocTag;
import at.fhtw.swen3.persistence.mapper.DatabaseMapper;
import at.fhtw.swen3.persistence.repository.DocTagRepository;
import at.fhtw.swen3.persistence.service.dto.DocTagDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DocTagServiceImplTest {

    @Mock
    private DocTagRepository repository;

    @Mock
    private DatabaseMapper mapper;

    @InjectMocks
    private DocTagServiceImpl service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAllByIdWithValidIds() {
        // Arrange
        List<Long> validIds = new ArrayList<>();
        validIds.add(1L);
        validIds.add(2L);

        List<DocTag> docTags = new ArrayList<>();
        DocTag tag1 = new DocTag();
        tag1.setId(1L);
        docTags.add(tag1);

        DocTag tag2 = new DocTag();
        tag2.setId(2L);
        docTags.add(tag2);

        List<DocTagDTO> expectedDTOs = new ArrayList<>();
        DocTagDTO dto1 = new DocTagDTO();
        dto1.setId(1L);
        expectedDTOs.add(dto1);

        DocTagDTO dto2 = new DocTagDTO();
        dto2.setId(2L);
        expectedDTOs.add(dto2);

        when(repository.findAllById(validIds)).thenReturn(docTags);
        when(mapper.toDocTagDTOs(docTags)).thenReturn(expectedDTOs);

        // Act
        List<DocTagDTO> resultDTOs = service.findAllById(validIds);

        // Assert
        assertNotNull(resultDTOs);
        assertEquals(2, resultDTOs.size());
        assertEquals(1L, resultDTOs.get(0).getId());
        assertEquals(2L, resultDTOs.get(1).getId());
    }

    @Test
    void testFindAllByIdWithEmptyIds() {
        // Arrange
        List<Long> emptyIds = new ArrayList<>();

        // Act
        List<DocTagDTO> resultDTOs = service.findAllById(emptyIds);

        // Assert
        assertNull(resultDTOs);
    }

    @Test
    void testFindAllByIdWithNullIds() {
        // Act
        List<DocTagDTO> resultDTOs = service.findAllById(null);

        // Assert
        assertNull(resultDTOs);
    }

    @Test
    void testFindAllByIdWithInvalidIds() {
        // Arrange
        List<Long> invalidIds = new ArrayList<>();
        invalidIds.add(999L);

        when(repository.findAllById(invalidIds)).thenReturn(new ArrayList<>());

        // Act
        List<DocTagDTO> resultDTOs = service.findAllById(invalidIds);

        // Assert
        assertEquals("[]", resultDTOs.toString());
    }

}
