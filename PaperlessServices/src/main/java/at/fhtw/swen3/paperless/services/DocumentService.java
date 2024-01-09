package at.fhtw.swen3.paperless.services;

import at.fhtw.swen3.paperless.repository.DocumentRepository;
import org.springframework.stereotype.Service;

@Service
public class DocumentService {
    private final DocumentRepository documentRepository;

    public DocumentService(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    public String updateContent(String content, long id) {
        documentRepository.updateContent(content, id);
        return documentRepository.findTitleById(id);
    }
}
