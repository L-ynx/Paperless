package at.fhtw.swen3.persistence.service;

import at.fhtw.swen3.persistence.entity.DocumentType;
import at.fhtw.swen3.persistence.mapper.DatabaseMapper;
import at.fhtw.swen3.persistence.repository.DocumentTypeRepository;
import at.fhtw.swen3.persistence.service.dto.DocumentTypeDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DocumentTypeServiceImplTest {

    @Mock
    private DocumentTypeRepository documentTypeRepository;

    @Mock
    private DatabaseMapper databaseMapper;

    @InjectMocks
    private DocumentTypeServiceImpl documentTypeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCreateDocumentType() {
        DocumentTypeDTO inputDTO = new DocumentTypeDTO(/* initialize with required values */);
        DocumentType inputEntity = new DocumentType(/* initialize with required values */);
        DocumentType savedEntity = new DocumentType(/* initialize with saved entity data */);

        when(databaseMapper.toEntity(inputDTO)).thenReturn(inputEntity);
        when(documentTypeRepository.save(inputEntity)).thenReturn(savedEntity);
        when(databaseMapper.toDTO(savedEntity)).thenReturn(inputDTO);

        DocumentTypeDTO result = documentTypeService.create(inputDTO);

        assertNotNull(result);
        // Add assertions for the expected results based on your specific use case
    }

    @Test
    void testFindDocumentTypeById() {
        Long id = 1L;
        DocumentTypeDTO expectedDTO = new DocumentTypeDTO(/* initialize with expected DTO data */);
        DocumentType expectedEntity = new DocumentType(/* initialize with expected entity data */);

        when(documentTypeRepository.findById(id)).thenReturn(Optional.of(expectedEntity));
        when(databaseMapper.toDTO(expectedEntity)).thenReturn(expectedDTO);

        DocumentTypeDTO result = documentTypeService.findById(id);

        assertNotNull(result);
        // Add assertions for the expected results based on your specific use case
    }

    @Test
    void testFindDocumentTypeByIdNotFound() {
        Long id = 1L;

        when(documentTypeRepository.findById(id)).thenReturn(Optional.empty());

        DocumentTypeDTO result = documentTypeService.findById(id);

        assertNull(result);
        // Add assertions for the expected results based on your specific use case
    }

    // You can write similar tests for other methods such as update, delete, and findAll
}
