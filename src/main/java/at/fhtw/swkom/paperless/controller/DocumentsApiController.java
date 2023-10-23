package at.fhtw.swkom.paperless.controller;

import at.fhtw.swkom.paperless.services.dto.*;
import jakarta.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-10-23T11:25:43.450871Z[Etc/UTC]")
@Controller
@RequestMapping("${openapi.paperlessRestServer.base-path:}")
public class DocumentsApiController implements DocumentsApi {

    private final NativeWebRequest request;

    @Autowired
    public DocumentsApiController(NativeWebRequest request) {
        this.request = request;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
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
        GetDocuments200Response documentTypes = new GetDocuments200Response();
        documentTypes.setCount(1);
        return new ResponseEntity<>(documentTypes, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UpdateDocument200Response> updateDocument(Integer id, UpdateDocumentRequest updateDocumentRequest) {
        UpdateDocument200Response documentTypes = new UpdateDocument200Response();
        documentTypes.setContent("PUT /api/documents/{id}");
        return new ResponseEntity<>(documentTypes, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> uploadDocument(String title, OffsetDateTime created, Integer documentType, List<Integer> tags, Integer correspondent, List<MultipartFile> document) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
