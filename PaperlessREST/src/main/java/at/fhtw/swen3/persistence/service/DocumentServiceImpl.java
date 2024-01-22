package at.fhtw.swen3.persistence.service;

import at.fhtw.swen3.persistence.entity.Document;
import at.fhtw.swen3.persistence.mapper.DatabaseMapper;
import at.fhtw.swen3.persistence.repository.DocumentRepository;
import at.fhtw.swen3.persistence.service.dto.DocumentDTO;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DocumentServiceImpl implements DocumentService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DocumentServiceImpl.class);

    private final RabbitTemplate rabbitTemplate;
    private final DocumentRepository repository;
    private final SearchIndexService searchIndexService;

    protected final DatabaseMapper mapper;

    @Autowired
    public DocumentServiceImpl(RabbitTemplate rabbitTemplate, DocumentRepository repository, DatabaseMapper mapper, SearchIndexService searchIndexService) {
        this.rabbitTemplate = rabbitTemplate;
        this.repository = repository;
        this.mapper = mapper;
        this.searchIndexService = searchIndexService;
    }

    @Override
    public DocumentDTO findById(Integer id) {
        try {
            DocumentDTO result = repository.findById(Long.valueOf(id)).map(mapper::toDTO).orElseThrow(() -> new RuntimeException("Document not found"));
            LOGGER.info("Document found: {}", result.getId());
            return result;
        } catch (RuntimeException e) {
            LOGGER.warn(e.getMessage());
        }
        return null;
    }

    @Override
    public List<DocumentDTO> findAll() {
        List<Document> documents = repository.findAll();
        LOGGER.info("Documents found: {}", documents.stream().map(Document::getId).collect(Collectors.toList()));
        Object queue = rabbitTemplate.receiveAndConvert("rabbitQueue");
        LOGGER.info("Message received from RabbitMQ: {}", queue);
        return mapper.toDTOs(documents);
    }


    @Override
    public Document saveDocument(Document document) {
        return repository.save(document);
    }

    @Override
    public void deleteDocument(Integer id) {
        repository.deleteById(Long.valueOf(id));
        searchIndexService.deleteDocumentById(id);
    }

    @Override
    public void updateDocument(Document document) {
        repository.save(document);
        searchIndexService.updateDocument(document);
    }

    @Override
    public byte[] getThumbnail(Integer id) {
        byte[] imageBytes = new byte[0];
        try {
            BufferedImage image = ImageIO.read(new ClassPathResource("Images/pic.jpeg").getInputStream());
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(image, "jpeg", baos);
            baos.flush();
            imageBytes = baos.toByteArray();
            LOGGER.info("Thumbnail for id: {} found ", id);
        } catch (IOException e) {
            LOGGER.warn(e.getMessage());
        }
        return imageBytes;
    }

    @Override
    public List<Document> searchDocuments(String query) {
        //no need to execute a query against the db, elastic search already contains
        //all the data we need in order to return useful results.
        try {
            return searchIndexService.searchDocument(query);
        } catch (IOException e) {
            LOGGER.warn(e.getMessage());
        }

        return null;
    }

    @Override
    public List<Document> fetchAllDocuments() {
        return this.repository.findAll();
    }

    @Override
    public List<Document> handleGetDocuments(String query) {

        if (query == null || query.isEmpty()) {
            return fetchAllDocuments();
        } else {
            return searchDocuments(query);
        }

    }
}
