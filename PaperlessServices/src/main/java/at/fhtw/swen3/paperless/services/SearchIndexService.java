package at.fhtw.swen3.paperless.services;

import at.fhtw.swen3.paperless.entity.Document;
import co.elastic.clients.elasticsearch._types.Result;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface SearchIndexService {
    Result indexDocument(Document document) throws IOException;

    Optional<Document> getDocumentById(int id);

    boolean deleteDocumentById(int id);

}