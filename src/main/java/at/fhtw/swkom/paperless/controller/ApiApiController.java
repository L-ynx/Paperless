package at.fhtw.swkom.paperless.controller;

import at.fhtw.swkom.paperless.services.dto.Correspondent;
import org.springframework.format.annotation.DateTimeFormat;
import at.fhtw.swkom.paperless.services.dto.DocTag;
import at.fhtw.swkom.paperless.services.dto.Document;
import at.fhtw.swkom.paperless.services.dto.DocumentType;
import at.fhtw.swkom.paperless.services.dto.NewCorrespondent;
import at.fhtw.swkom.paperless.services.dto.NewDocumentType;
import at.fhtw.swkom.paperless.services.dto.NewTag;
import java.time.OffsetDateTime;
import at.fhtw.swkom.paperless.services.dto.UserInfo;


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

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-10-17T11:29:20.935609Z[Etc/UTC]")
@Controller
@RequestMapping("${openapi.mockServer.base-path:}")
public class ApiApiController implements ApiApi {

    private final NativeWebRequest request;

    @Autowired
    public ApiApiController(NativeWebRequest request) {
        this.request = request;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

}
