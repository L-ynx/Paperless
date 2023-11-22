package at.fhtw.swen3.paperless.controller;

import at.fhtw.swen3.paperless.services.dto.*;
import jakarta.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.Collections;
import java.util.Optional;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-10-23T11:25:43.450871Z[Etc/UTC]")
@Controller
@RequestMapping("${openapi.paperlessRestServer.base-path:}")
@CrossOrigin(origins = "http://localhost:8080")
public class ConfigApiController implements ConfigApi {

    private final NativeWebRequest request;

    @Autowired
    public ConfigApiController(NativeWebRequest request) {
        this.request = request;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<CreateStoragePath200Response> createStoragePath(CreateStoragePathRequest createStoragePathRequest) {
        CreateStoragePath200Response response = new CreateStoragePath200Response();
        response.setName("POST /api/storage_paths");
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<Void> deleteStoragePath(Integer id) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @Override
    public ResponseEntity<Void> get() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<GetStoragePaths200Response> getStoragePaths(Integer page, Boolean fullPerms) {
        GetStoragePaths200Response response = new GetStoragePaths200Response();
        GetStoragePaths200ResponseResultsInner inner = new GetStoragePaths200ResponseResultsInner();
        inner.setName("GET /api/storage_paths");
        response.setResults(Collections.singletonList(inner));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UpdateStoragePath200Response> updateStoragePath(Integer id, UpdateStoragePathRequest updateStoragePathRequest) {
        UpdateStoragePath200Response response = new UpdateStoragePath200Response();
        response.setName("PUT /api/storage_paths/{id}");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CreateUISettings200Response> createUISettings(CreateUISettingsRequest createUISettingsRequest) {
        CreateUISettings200Response response = new CreateUISettings200Response();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }



}
