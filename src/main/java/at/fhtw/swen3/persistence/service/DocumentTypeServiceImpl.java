package at.fhtw.swen3.persistence.service;

import at.fhtw.swen3.persistence.entity.DocumentType;
import at.fhtw.swen3.persistence.mapper.DatabaseMapper;
import at.fhtw.swen3.persistence.repository.DocumentTypeRepository;
import at.fhtw.swen3.persistence.service.dto.DocumentTypeDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentTypeServiceImpl implements DocumentTypeService {

    private final DocumentTypeRepository documentTypeRepository;
    protected final DatabaseMapper databaseMapper;

    @Autowired
    public DocumentTypeServiceImpl(
            DocumentTypeRepository documentTypeRepository,
            DatabaseMapper databaseMapper
    ) {
        this.documentTypeRepository = documentTypeRepository;
        this.databaseMapper = databaseMapper;
    }

    public DocumentTypeDTO create(DocumentTypeDTO documentTypeDTO) {
        DocumentType documentType = databaseMapper.toEntity(documentTypeDTO);
        DocumentType savedDocumentType = documentTypeRepository.save(documentType);
        return databaseMapper.toDTO(savedDocumentType);
    }

    public DocumentTypeDTO findById(Long id) {
        DocumentType documentType = documentTypeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("DocumentType not found with id: " + id));

        return databaseMapper.toDTO(documentType);
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
