package at.fhtw.swkom.paperless.controller;

import at.fhtw.swkom.paperless.FakeDataProvider;
import at.fhtw.swkom.paperless.services.dto.Correspondent;
import at.fhtw.swkom.paperless.services.dto.Document;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.Optional;
import jakarta.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-10-17T11:29:20.935609Z[Etc/UTC]")
@Controller
@RequestMapping("${openapi.mockServer.base-path:}")
public class ApiApiController implements ApiApi {

    private final FakeDataProvider fakeDataProvider;

    @Autowired
    public ApiApiController(FakeDataProvider fakeDataProvider) {
        this.fakeDataProvider = fakeDataProvider;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    @Override
    public ResponseEntity<Correspondent> getCorrespondentById(Long id) {
        // Use the FakeDataProvider to generate a fake Correspondent
        Correspondent fakeCorrespondent = fakeDataProvider.getFakeCorrespondent();
        return new ResponseEntity<>(fakeCorrespondent, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Document> getDocumentById(Long id) {
        // Use the FakeDataProvider to generate a fake Document
        Document fakeDocument = fakeDataProvider.getFakeDocument();
        return new ResponseEntity<>(fakeDocument, HttpStatus.OK);
    }
}