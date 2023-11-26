package at.fhtw.swen3.persistence.service;

import at.fhtw.swen3.paperless.config.SpringDocConfiguration;
import at.fhtw.swen3.persistence.entity.Document;
import at.fhtw.swen3.persistence.mapper.DatabaseMapper;
import at.fhtw.swen3.persistence.repository.DocumentRepository;
import at.fhtw.swen3.persistence.service.dto.CorrespondentDTO;
import at.fhtw.swen3.persistence.service.dto.DocTagDTO;
import at.fhtw.swen3.persistence.service.dto.DocumentDTO;
import at.fhtw.swen3.persistence.service.dto.DocumentTypeDTO;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DocumentServiceImpl implements DocumentService {
    static final Logger LOGGER = LoggerFactory.getLogger(DocumentServiceImpl.class);
    final AtomicInteger messageCount = new AtomicInteger(0);

    private final RabbitTemplate rabbitTemplate;
    private final DocumentRepository repository;
    private final DocumentTypeService documentTypeService;
    private final CorrespondentService correspondentService;
    private final DocTagService docTagService;
    protected final DatabaseMapper mapper;

    @Autowired
    public DocumentServiceImpl(RabbitTemplate rabbitTemplate, DocumentRepository repository, DocumentTypeService documentTypeService, CorrespondentService correspondentService, DocTagService docTagService, DatabaseMapper mapper) {
        this.rabbitTemplate = rabbitTemplate;
        this.repository = repository;
        this.documentTypeService = documentTypeService;
        this.correspondentService = correspondentService;
        this.docTagService = docTagService;
        this.mapper = mapper;
    }

    @Override
    public DocumentDTO findById(Integer id) {
        DocumentDTO result = repository.findById(Long.valueOf(id)).map(mapper::toDTO).orElseThrow(() -> new RuntimeException("Document not found"));
        LOGGER.info("Document found: {}", result);
        return result;
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
    public void create(String title, OffsetDateTime created, Integer documentType, List<Integer> tags, Integer correspondent, List<MultipartFile> documents) {
        DocumentTypeDTO type = documentTypeService.findById(Long.valueOf(documentType));
        CorrespondentDTO correspondentEntity = correspondentService.findById(Long.valueOf(correspondent));
        List<DocTagDTO> tagEntities = new ArrayList<>();
        if (tags != null && !tags.isEmpty())
            tagEntities = docTagService.findAllById(tags.stream().map(Long::valueOf).collect(Collectors.toList()));

        Document document = Document.builder()
                .title(title)
                .createdAt(created.toLocalDateTime())
                .documentType(mapper.toEntity(type))
                .correspondent(mapper.toEntity(correspondentEntity))
                .docTags(mapper.toDocTagsEntity(tagEntities))
                .build();

        repository.save(document);
        LOGGER.info("Document created: {}", document.getTitle());
        for (MultipartFile multipartFile : documents) {
            processMessage(multipartFile.getOriginalFilename());
        }
    }

    @RabbitListener(queues = SpringDocConfiguration.ECHO_IN_QUEUE_NAME)
    private void processMessage(String documents) {
        rabbitTemplate.convertAndSend(SpringDocConfiguration.EXCHANGE, SpringDocConfiguration.ECHO_OUT_QUEUE_NAME, documents, message -> {
            message.getMessageProperties().getHeaders().put(SpringDocConfiguration.ECHO_MESSAGE_COUNT_PROPERTY_NAME, messageCount.incrementAndGet());
            return message;
        });
        LOGGER.info("Message {} sent to RabbitMQ", messageCount.get());
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
            LOGGER.error(e.getMessage());
        }
        return imageBytes;
    }
}
