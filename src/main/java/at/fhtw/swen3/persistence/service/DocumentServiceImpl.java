package at.fhtw.swen3.persistence.service;

import at.fhtw.swen3.persistence.entity.Document;
import at.fhtw.swen3.persistence.mapper.DatabaseMapper;
import at.fhtw.swen3.persistence.repository.CorrespondentRepository;
import at.fhtw.swen3.persistence.repository.DocTagRepository;
import at.fhtw.swen3.persistence.repository.DocumentRepository;
import at.fhtw.swen3.persistence.repository.DocumentTypeRepository;
import at.fhtw.swen3.persistence.service.dto.DocumentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.OffsetDateTime;
import java.util.List;

@Service
public class DocumentServiceImpl implements DocumentService{
    private final DocumentRepository repository;
    private final DocumentTypeRepository documentTypeRepository;
    private final CorrespondentRepository correspondentRepository;
    private final DocTagRepository docTagRepository;

    protected final DatabaseMapper mapper;

    @Autowired
    public DocumentServiceImpl(DocumentRepository repository, DocumentTypeRepository documentTypeRepository, CorrespondentRepository correspondentRepository, DocTagRepository docTagRepository, DatabaseMapper mapper) {
        this.repository = repository;
        this.documentTypeRepository = documentTypeRepository;
        this.correspondentRepository = correspondentRepository;
        this.docTagRepository = docTagRepository;
        this.mapper = mapper;
    }

    @Override
    public DocumentDTO findById(Integer id) {
        return null;
    }

    @Override
    public List<DocumentDTO> findAll() {
        List<Document> documents = repository.findAll();
        return mapper.toDTOs(documents);
    }

    @Override
    public void create(String title, OffsetDateTime created, Integer documentType, List<Integer> tags, Integer correspondent, List<MultipartFile> documents) {
        //DocumentType type = documentTypeRepository.findById(Long.valueOf(documentType)).orElseThrow(() -> new RuntimeException("Document type not found"));

        //Correspondent correspondentEntity = correspondentRepository.findById(Long.valueOf(correspondent)).orElseThrow(() -> new RuntimeException("Correspondent not found"));

        //List<DocTag> tagEntities = docTagRepository.findAllById(tags.stream().map(Long::valueOf).collect(Collectors.toList()));

        Document document = Document.builder()
                .title(title)
                .createdAt(created.toLocalDateTime())
                //.documentType(type)
                //.docTags(tagEntities)
                //.correspondent(correspondentEntity)
                .build();

        repository.save(document);
    }
}
