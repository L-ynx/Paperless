package at.fhtw.swen3.persistence.service;

import at.fhtw.swen3.persistence.entity.Correspondent;
import at.fhtw.swen3.persistence.mapper.DatabaseMapper;
import at.fhtw.swen3.persistence.repository.CorrespondentRepository;
import at.fhtw.swen3.persistence.service.dto.CorrespondentDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CorrespondentServiceImplTest {

    @Mock
    private CorrespondentRepository repository;

    @Mock
    private DatabaseMapper mapper;

    @InjectMocks
    private CorrespondentServiceImpl service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindByIdWithValidId() {
        // Arrange
        Long validId = 1L;
        Correspondent correspondent = new Correspondent();
        correspondent.setId(validId);
        CorrespondentDTO expectedDTO = new CorrespondentDTO();
        expectedDTO.setId(validId);

        when(repository.findById(validId)).thenReturn(Optional.of(correspondent));
        when(mapper.toDTO(correspondent)).thenReturn(expectedDTO);

        // Act
        CorrespondentDTO resultDTO = service.findById(validId);

        // Assert
        assertNotNull(resultDTO);
        assertEquals(validId, resultDTO.getId());
    }

    @Test
    void testFindByIdWithInvalidId() {
        // Arrange
        Long invalidId = 999L;

        when(repository.findById(invalidId)).thenReturn(Optional.empty());

        // Act
        CorrespondentDTO resultDTO = service.findById(invalidId);

        // Assert
        assertNull(resultDTO);
    }

    @Test
    void testFindByIdWithNullId() {
        // Act
        CorrespondentDTO resultDTO = service.findById(null);

        // Assert
        assertNull(resultDTO);
    }
}
