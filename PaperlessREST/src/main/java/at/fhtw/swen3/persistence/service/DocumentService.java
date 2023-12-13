package at.fhtw.swen3.persistence.service;

import at.fhtw.swen3.persistence.entity.Document;
import at.fhtw.swen3.persistence.service.dto.DocumentDTO;

import java.util.List;

public interface DocumentService {
    DocumentDTO findById(Integer id);

    List<DocumentDTO> findAll();

    Document saveDocument(Document document);

    byte[] getThumbnail(Integer id);
}
