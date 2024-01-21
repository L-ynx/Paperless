package at.fhtw.swen3.paperless.controller;

import at.fhtw.swen3.paperless.exceptions.RabbitMQException;
import at.fhtw.swen3.paperless.exceptions.RestServerException;
import at.fhtw.swen3.paperless.services.dto.*;
import at.fhtw.swen3.persistence.mapper.DatabaseMapper;
import at.fhtw.swen3.persistence.service.*;
import at.fhtw.swen3.persistence.service.dto.CorrespondentDTO;
import at.fhtw.swen3.persistence.service.dto.DocTagDTO;
import at.fhtw.swen3.persistence.service.dto.DocumentDTO;
import at.fhtw.swen3.persistence.service.dto.DocumentTypeDTO;
import at.fhtw.swen3.persistence.service.messageQueue.MessageQueueService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-10-23T11:25:43.450871Z[Etc/UTC]")
@Controller
@RequestMapping("${openapi.paperlessRestServer.base-path:}")
@CrossOrigin(origins = "http://localhost:8080")
public class  DocumentsApiController implements DocumentsApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(DocumentsApiController.class);
    private final NativeWebRequest request;
    private final DocumentService documentService;
    private final DocumentTypeService documentTypeService;
    private final CorrespondentService correspondentService;
    private final DocTagService docTagService;
    private final MinIOService minIOService;
    private final MessageQueueService messageQueueService;
    private final DatabaseMapper mapper;

    @Autowired
    public DocumentsApiController(NativeWebRequest request, DocumentService documentService, DocumentTypeService documentTypeService, CorrespondentService correspondentService, DocTagService docTagService, DatabaseMapper mapper, MinIOService minIOService, MessageQueueService messageQueueService) {
        this.request = request;
        this.documentService = documentService;
        this.documentTypeService = documentTypeService;
        this.correspondentService = correspondentService;
        this.docTagService = docTagService;
        this.minIOService = minIOService;
        this.messageQueueService = messageQueueService;
        this.mapper = mapper;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<Void> apiCorrespondentsGet(Integer page, Integer pageSize) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> apiTagsGet(Integer page, Integer pageSize) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleteDocument(Integer id) {
        documentService.deleteDocument(id);
        minIOService.deleteObject(String.valueOf(id));

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<GetDocument200Response> getDocument(Integer id, Integer page, Boolean fullPerms) {
        DocumentDTO documentDTO = documentService.findById(id);

        GetDocument200ResponsePermissions permissions = getGetDocument200ResponsePermissions();
        GetDocument200Response response = getGetDocument200Response(id, documentDTO, permissions);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @NotNull
    private static GetDocument200Response getGetDocument200Response(Integer id, DocumentDTO documentDTO, GetDocument200ResponsePermissions permissions) {
        GetDocument200Response response = new GetDocument200Response();
        response.setId((int) documentDTO.getId());
        response.setTitle(documentDTO.getTitle());
        response.setContent(documentDTO.getContent());
        response.setOwner(1);
        response.setCreatedDate(documentDTO.getCreatedAt().toString());
        response.setAdded(documentDTO.getAddedAt().toString());
        response.setPermissions(permissions);
        response.setOriginalFileName(documentDTO.getTitle());
        response.setArchiveSerialNumber(id);
        response.setArchivedFileName(documentDTO.getTitle());
        response.setModified(documentDTO.getModified().toString());
        response.setStoragePath(1);
        response.setCorrespondent(1);
        response.setDocumentType(1);
        return response;
    }

    @NotNull
    private static GetDocument200ResponsePermissions getGetDocument200ResponsePermissions() {
        GetDocument200ResponsePermissions permissions = new GetDocument200ResponsePermissions();
        permissions.setView(new GetDocument200ResponsePermissionsView());
        permissions.setChange(new GetDocument200ResponsePermissionsView());
        permissions.getView().addUsersItem(1);
        permissions.getView().addUsersItem(2);
        permissions.getChange().addUsersItem(1);
        permissions.getChange().addUsersItem(2);
        permissions.getView().addGroupsItem(1);
        permissions.getView().addGroupsItem(2);
        permissions.getChange().addGroupsItem(1);
        permissions.getChange().addGroupsItem(2);
        return permissions;
    }

    @Override
    public ResponseEntity<GetDocumentMetadata200Response> getDocumentMetadata(Integer id) {
        GetDocumentMetadata200Response documentTypes = new GetDocumentMetadata200Response();
        documentTypes.setLang("GET /api/documents/{id}/metadata");
        DocumentDTO documentDTO = documentService.findById(id);
        documentTypes.setOriginalChecksum(documentDTO.getChecksum());
        documentTypes.setOriginalSize((int) documentDTO.getFilesize());
        documentTypes.setOriginalMimeType(documentDTO.getMimeType());
        documentTypes.setOriginalFilename(documentDTO.getOriginalName());
        documentTypes.setMediaFilename(documentDTO.getTitle());

        return new ResponseEntity<>(documentTypes, HttpStatus.OK);
    }


    @Override
    public ResponseEntity<GetDocuments200Response> getDocuments(Integer page, Integer pageSize, String query, String ordering, List<Integer> tagsIdAll, Integer documentTypeId, Integer storagePathIdIn, Integer correspondentId, Boolean truncateContent) {
        List<DocumentDTO> documents = null;
        if (query == null) {
            documents = documentService.findAll();
        }
        else
            try {
            documents = mapper.toDTOs(documentService.searchDocuments(query));
            } catch (IOException e){
                LOGGER.error("Error searching for documents! \n");
        }

        GetDocuments200Response response = new GetDocuments200Response();
        response.setCount(documents.size());
        List<GetDocuments200ResponseResultsInner> innerResults = new ArrayList<>();
        for (DocumentDTO documentDTO : documents) {
            GetDocuments200ResponseResultsInner inner = new GetDocuments200ResponseResultsInner();
            inner.id((int) documentDTO.getId());
            inner.title(documentDTO.getTitle());
            inner.content(documentDTO.getContent());
            // inner.created(documentDTO.getCreatedAt().toString());
            //inner.documentType((int) documentDTO.getDocumentType().getMatchingAlgorithm());
            //inner.correspondent((int) documentDTO.getCorrespondent().getDocumentCount());
            //List<Integer> tags = documentDTO.getDocTags().stream().map(tag -> Math.toIntExact(tag.getMatchingAlgorithm())).toList();
            //inner.tags(tags);
            innerResults.add(inner);
        }
        response.setResults(innerResults);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UpdateDocument200Response> updateDocument(Integer id, UpdateDocumentRequest updateDocumentRequest) {
        UpdateDocument200Response documentTypes = new UpdateDocument200Response();
        documentTypes.setContent("PUT /api/documents/{id}");

        DocumentDTO documentDTO = documentService.findById(id);
        documentDTO.setTitle(updateDocumentRequest.getTitle());
        documentDTO.setContent(updateDocumentRequest.getContent());
        documentDTO.setCreatedAt(LocalDateTime.parse(updateDocumentRequest.getCreatedDate()));
        documentDTO.setDocumentType(mapper.toEntity(documentTypeService.findById(Long.valueOf(updateDocumentRequest.getDocumentType()))));

        documentService.saveDocument(mapper.toEntity(documentDTO));

        return new ResponseEntity<>(documentTypes, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> uploadDocument(String title, OffsetDateTime created, Integer documentType, List<Integer> tags, Integer correspondent, List<MultipartFile> document) {
        DocumentDTO documentDTO;
        DocumentDTO savedDocument;

        try {
            documentDTO = createDocumentDTO(title, created, documentType, tags, correspondent, document);
        } catch (Exception e) {
            LOGGER.error("Error creating document", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        try {
            savedDocument = mapper.toDTO(documentService.saveDocument(mapper.toEntity(documentDTO)));

            for (MultipartFile multipartFile : document) {
                minIOService.saveObject(multipartFile, String.valueOf(savedDocument.getId()));
                messageQueueService.processMessage(multipartFile.getOriginalFilename());
            }

            //minIOService.saveObject(savedDocument);


            //documentService.create(title, created, documentType, tags, correspondent, document);
            LOGGER.info("Document uploaded successfully");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RabbitMQException ex) {
            LOGGER.error("Error sending message to RabbitMQ", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        } catch (RestServerException ex) {
            LOGGER.error("Error uploading document", ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @Override
    public ResponseEntity<ByteArrayResource> getDocumentThumb(Integer id) {
        byte[] imageBytes = documentService.getThumbnail(id);
        ByteArrayResource resource = new ByteArrayResource(imageBytes);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        headers.setContentLength(imageBytes.length);
        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }

    private DocumentDTO createDocumentDTO(String title, OffsetDateTime created, Integer documentType, List<Integer> tags, Integer correspondent, List<MultipartFile> document) throws IOException {
        DocumentTypeDTO type = documentTypeService.findById(documentType == null ? null : Long.valueOf(documentType));
        CorrespondentDTO correspondentEntity = correspondentService.findById(correspondent == null ? null : Long.valueOf(correspondent));
        List<DocTagDTO> tagEntities = docTagService.findAllById(tags == null ? null : tags.stream().map(Long::valueOf).collect(Collectors.toList()));

        return DocumentDTO.builder()
                .title(title == null ? document.get(0).getOriginalFilename() : title)
                .originalName(document.get(0).getOriginalFilename())
                .owner("admin")
                .createdAt(created == null ? LocalDateTime.now() : created.toLocalDateTime())
                .addedAt(LocalDateTime.now())
                .modified(LocalDateTime.now())
                .filesize(document.get(0).getSize())
                .mimeType(document.get(0).getContentType())
                .checksum(String.valueOf(Arrays.hashCode(document.get(0).getBytes())))
                .documentType(mapper.toEntity(type))
                .storagePath("storage")
                .correspondent(mapper.toEntity(correspondentEntity))
                .docTags(mapper.toDocTagsEntity(tagEntities))
                .build();
    }



}
