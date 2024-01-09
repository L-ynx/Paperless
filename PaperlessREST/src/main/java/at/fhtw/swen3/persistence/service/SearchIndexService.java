package at.fhtw.swen3.persistence.service;


import at.fhtw.swen3.persistence.entity.Document;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface SearchIndexService {

    Optional<Document> getDocumentById(int id);

    boolean deleteDocumentById(int id);

    List<Document> searchDocument(String string) throws IOException;
}