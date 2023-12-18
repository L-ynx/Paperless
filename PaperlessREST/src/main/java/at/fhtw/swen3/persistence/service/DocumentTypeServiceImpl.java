package at.fhtw.swen3.persistence.service;

import at.fhtw.swen3.persistence.entity.DocumentType;
import at.fhtw.swen3.persistence.mapper.DatabaseMapper;
import at.fhtw.swen3.persistence.repository.DocumentTypeRepository;
import at.fhtw.swen3.persistence.service.dto.DocumentTypeDTO;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class DocumentTypeServiceImpl implements DocumentTypeService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DocumentTypeServiceImpl.class);

    private final DocumentTypeRepository documentTypeRepository;
    protected final DatabaseMapper databaseMapper;

    @Autowired
    public DocumentTypeServiceImpl(DocumentTypeRepository documentTypeRepository, DatabaseMapper databaseMapper) {
        this.documentTypeRepository = documentTypeRepository;
        this.databaseMapper = databaseMapper;
    }

    public DocumentTypeDTO create(DocumentTypeDTO documentTypeDTO) {
        try {
            DocumentType documentType = databaseMapper.toEntity(documentTypeDTO);
            DocumentType savedDocumentType = documentTypeRepository.save(documentType);
            LOGGER.info("DocumentType saved: {}", savedDocumentType);
            return databaseMapper.toDTO(savedDocumentType);
        } catch (Exception e) {
            LOGGER.warn("DocumentType not saved with name: {}", documentTypeDTO.getName());
            return null;
        }
    }

    public DocumentTypeDTO findById(Long id) {
        try {
            if (id == null) throw new EntityNotFoundException();
            DocumentType documentType = documentTypeRepository.findById(id).orElseThrow(EntityNotFoundException::new);
            LOGGER.info("DocumentType found: {}", documentType);
            return databaseMapper.toDTO(documentType);
        } catch (EntityNotFoundException e) {
            LOGGER.warn("DocumentType not found with id: {}", id);
            return null;
        }
    }

    @Override
    public DocumentTypeDTO update(DocumentTypeDTO documentTypeDTO) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<DocumentTypeDTO> findAll() {
        return null;
    }
}
