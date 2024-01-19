package at.fhtw.swen3.persistence.service;


import at.fhtw.swen3.persistence.entity.Document;

import java.io.IOException;
import java.util.List;

public interface SearchIndexService {

    List<Document> searchDocument(String string) throws IOException;
    boolean deleteDocumentById(int id);
}