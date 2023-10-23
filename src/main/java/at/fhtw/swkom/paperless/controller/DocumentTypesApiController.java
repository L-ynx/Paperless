package at.fhtw.swkom.paperless.controller;

import at.fhtw.swkom.paperless.services.dto.CreateDocumentType200Response;
import at.fhtw.swkom.paperless.services.dto.CreateDocumentTypeRequest;
import at.fhtw.swkom.paperless.services.dto.GetDocumentTypes200Response;
import at.fhtw.swkom.paperless.services.dto.UpdateDocumentType200Response;
import at.fhtw.swkom.paperless.services.dto.UpdateDocumentTypeRequest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.context.request.NativeWebRequest;

import jakarta.validation.constraints.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import jakarta.annotation.Generated;

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

}
