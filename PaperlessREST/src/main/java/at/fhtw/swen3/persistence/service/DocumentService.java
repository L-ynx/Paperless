package at.fhtw.swen3.persistence.service;

import at.fhtw.swen3.persistence.entity.Document;
import at.fhtw.swen3.persistence.service.dto.DocumentDTO;

import java.io.IOException;
import java.util.List;

public interface DocumentService {
    DocumentDTO findById(Integer id);

    List<DocumentDTO> findAll();

    Document saveDocument(Document document);

    void updateDocument(Document document);

    void deleteDocument(Integer id);

    byte[] getThumbnail(Integer id);

    List<Document> searchDocuments(String query) throws IOException;

    List<Document> fetchAllDocuments();
    List<Document> handleGetDocuments(String query) throws IOException;
}