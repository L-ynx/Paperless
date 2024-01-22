package at.fhtw.swen3.paperless.services;

import at.fhtw.swen3.paperless.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceDocumentServiceImpl implements ServiceDocumentService {
    private final DocumentRepository documentRepository;

    @Autowired
    public ServiceDocumentServiceImpl(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    public String updateContent(String content, long id) {
        documentRepository.updateContent(content, id);
        return documentRepository.findTitleById(id);
    }
}
