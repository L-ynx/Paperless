package at.fhtw.swen3.paperless.controller;

import at.fhtw.swen3.paperless.exceptions.RabbitMQException;
import at.fhtw.swen3.paperless.exceptions.RestServerException;
import at.fhtw.swen3.paperless.services.dto.*;
import at.fhtw.swen3.persistence.service.DocumentService;
import at.fhtw.swen3.persistence.service.dto.DocumentDTO;
import jakarta.annotation.Generated;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-10-23T11:25:43.450871Z[Etc/UTC]")
@Controller
@RequestMapping("${openapi.paperlessRestServer.base-path:}")
@CrossOrigin(origins = "http://localhost:8080")
public class DocumentsApiController implements DocumentsApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(DocumentsApiController.class);
    private final NativeWebRequest request;
    private final DocumentService documentService;

    @Autowired
    public DocumentsApiController(NativeWebRequest request, DocumentService documentService) {
        this.request = request;
        this.documentService = documentService;
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
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<GetDocument200Response> getDocument(Integer id, Integer page, Boolean fullPerms) {
        GetDocument200Response documentTypes = new GetDocument200Response();
        documentTypes.setContent("GET /api/documents/{id}");
        return new ResponseEntity<>(documentTypes, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<GetDocumentMetadata200Response> getDocumentMetadata(Integer id) {
        GetDocumentMetadata200Response documentTypes = new GetDocumentMetadata200Response();
        documentTypes.setLang("GET /api/documents/{id}/metadata");
        return new ResponseEntity<>(documentTypes, HttpStatus.OK);
    }


    @Override
    public ResponseEntity<GetDocuments200Response> getDocuments(Integer page, Integer pageSize, String query, String ordering, List<Integer> tagsIdAll, Integer documentTypeId, Integer storagePathIdIn, Integer correspondentId, Boolean truncateContent) {
        List<DocumentDTO> documents = documentService.findAll();

        GetDocuments200Response response = new GetDocuments200Response();
        response.setCount(documents.size());
        List<GetDocuments200ResponseResultsInner> innerResults = new ArrayList<>();
        for (DocumentDTO documentDTO : documents) {
            GetDocuments200ResponseResultsInner inner = new GetDocuments200ResponseResultsInner();
            inner.id((int) documentDTO.getId());
            inner.title(documentDTO.getTitle());
            inner.content(documentDTO.getContent());
            inner.created(documentDTO.getCreatedAt().toString());
            //inner.documentType((int) documentDTO.getDocumentType().getMatchingAlgorithm());
            //inner.correspondent((int) documentDTO.getCorrespondent().getDocumentCount());
            List<Integer> tags = documentDTO.getDocTags().stream().map(tag -> Math.toIntExact(tag.getMatchingAlgorithm())).toList();
            inner.tags(tags);
            innerResults.add(inner);
        }
        response.setResults(innerResults);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UpdateDocument200Response> updateDocument(Integer id, UpdateDocumentRequest updateDocumentRequest) {
        UpdateDocument200Response documentTypes = new UpdateDocument200Response();
        documentTypes.setContent("PUT /api/documents/{id}");
        return new ResponseEntity<>(documentTypes, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> uploadDocument(String title, OffsetDateTime created, Integer documentType, List<Integer> tags, Integer correspondent, List<MultipartFile> document) {
        try {
            documentService.create(title, created, documentType, tags, correspondent, document);
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

}
