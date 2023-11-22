package at.fhtw.swen3.persistence.service;


import at.fhtw.swen3.persistence.service.dto.DocumentTypeDTO;

import java.util.List;

public interface DocumentTypeService {
    DocumentTypeDTO create(DocumentTypeDTO documentTypeDTO);

    DocumentTypeDTO update(DocumentTypeDTO documentTypeDTO);

    void delete(Long id);

    DocumentTypeDTO findById(Long id);

    List<DocumentTypeDTO> findAll();
}
