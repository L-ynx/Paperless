package at.fhtw.swen3.persistence.service;

import at.fhtw.swen3.persistence.entity.Document;
import at.fhtw.swen3.persistence.service.dto.DocumentDTO;
import org.springframework.web.multipart.MultipartFile;

import java.time.OffsetDateTime;
import java.util.List;

public interface DocumentService {
    DocumentDTO findById(Integer id);

    List<DocumentDTO> findAll();

    void create(String title, OffsetDateTime created, Integer documentType, List<Integer> tags, Integer correspondent, List<MultipartFile> document);

    Document saveDocument(Document document);

    byte[] getThumbnail(Integer id);
}
