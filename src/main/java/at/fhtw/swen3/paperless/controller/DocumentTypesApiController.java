package at.fhtw.swkom.paperless.controller;

import at.fhtw.swkom.paperless.services.dto.*;
import jakarta.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.Collections;
import java.util.Optional;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-10-23T11:25:43.450871Z[Etc/UTC]")
@Controller
@RequestMapping("${openapi.paperlessRestServer.base-path:}")
public class DocumentTypesApiController implements DocumentTypesApi {

    private final NativeWebRequest request;

    @Autowired
    public DocumentTypesApiController(NativeWebRequest request) {
        this.request = request;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<CreateDocumentType200Response> createDocumentType(CreateDocumentTypeRequest createDocumentTypeRequest) {
        CreateDocumentType200Response response = new CreateDocumentType200Response();
        response.setId(0);
        response.setSlug("slug");
        response.setName("name");
        response.setMatch("match");
        response.setMatchingAlgorithm(0);
        response.setIsInsensitive(true);
        response.setOwner(0);
        response.userCanChange(true);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<GetDocumentTypes200Response> getDocumentTypes(Integer page, Boolean fullPerms) {
        GetDocumentTypes200ResponseResultsInner inner = new GetDocumentTypes200ResponseResultsInner();
        inner.setId(0);
        inner.setSlug("slug");
        inner.setName("name");
        inner.setMatch("match");
        inner.setMatchingAlgorithm(0);
        inner.setIsInsensitive(true);
        inner.setOwner(0);
        inner.permissions(new UpdateDocumentRequestPermissions());

        GetDocumentTypes200Response response = new GetDocumentTypes200Response();
        response.setCount(0);
        response.setNext(0);
        response.setPrevious(0);
        response.setAll(Collections.singletonList(0));
        response.setResults(Collections.singletonList(inner));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleteDocumentType(Integer id) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<UpdateDocumentType200Response> updateDocumentType(Integer id, UpdateDocumentTypeRequest updateDocumentTypeRequest) {
        UpdateDocumentType200Response response = new UpdateDocumentType200Response();
        response.setId(0);
        response.setSlug("slug");
        response.setName("name");
        response.setMatch("match");
        response.setMatchingAlgorithm(0);
        response.setIsInsensitive(true);
        response.setOwner(0);
        response.userCanChange(true);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
