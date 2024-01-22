package at.fhtw.swen3.persistence.service;

import at.fhtw.swen3.paperless.config.ElasticSearchConfig;
import at.fhtw.swen3.persistence.entity.Document;
import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.Result;
import co.elastic.clients.elasticsearch.core.DeleteResponse;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@Slf4j
public class SearchIndexServiceImpl implements SearchIndexService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SearchIndexServiceImpl.class);
    private final ElasticsearchClient esClient;

    @Autowired
    public SearchIndexServiceImpl(ElasticsearchClient esClient) {
        this.esClient = esClient;

        try {
            if (!esClient.indices().exists(
                    i -> i.index(ElasticSearchConfig.DOCUMENTS_INDEX_NAME)
            ).value()) {
                esClient.indices().create(c -> c
                        .index(ElasticSearchConfig.DOCUMENTS_INDEX_NAME)
                );
            }
        } catch (IOException e) {
            LOGGER.error("Error creating index \n" + e.getMessage());
        }
    }

    @Override
    public List<Document> searchDocument(String searchString) {
        //search document with query
        try {
            SearchResponse<Document> searchResponse = this.esClient.search(s -> s
                    .index(ElasticSearchConfig.DOCUMENTS_INDEX_NAME)
                    .query(q -> q
                            .multiMatch(
                                    m -> m.fields("content", "title").fuzziness("AUTO").query("*" + searchString + "*")
                            )), Document.class);

            return searchResponse.hits().hits().stream().map(Hit::source).toList();
        } catch (IOException e) {
            LOGGER.error("Error searching for documents \n" + e.getMessage());
            return null;
        }
    }

    @Override
    public void updateDocument(Document document) {
        //update document with ElasticSearch
        String docId = String.valueOf(document.getId());

        try {
            esClient.update(u -> u
                            .index(ElasticSearchConfig.DOCUMENTS_INDEX_NAME)
                            .id(docId)
                            .doc(document),
                    Document.class
            );
            LOGGER.info("Updated document id=" + docId + " in elasticsearch");
        } catch (IOException e) {
            LOGGER.error("Error updating document \n" + e.getMessage());
        }
    }


    @Override
    public boolean deleteDocumentById(int id) {
        DeleteResponse result = null;
        try {
            result = esClient.delete(d -> d.index(ElasticSearchConfig.DOCUMENTS_INDEX_NAME).id(String.valueOf(id)));
            LOGGER.info("Deleted document id=" + id + " from elasticsearch: " + result);
        } catch (IOException e) {
            LOGGER.warn("Failed to delete document id=" + id + " from elasticsearch: " + e);
        }

        if (result == null)
            return false;
        if (result.result() != Result.Deleted)
            LOGGER.warn(result.toString());
        return result.result() == Result.Deleted;
    }

}