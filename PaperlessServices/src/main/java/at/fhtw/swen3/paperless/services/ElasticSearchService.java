package at.fhtw.swen3.paperless.services;

import at.fhtw.swen3.paperless.config.ElasticSearchConfig;
import at.fhtw.swen3.paperless.entity.Document;
import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.Result;
import co.elastic.clients.elasticsearch.core.GetResponse;
import co.elastic.clients.elasticsearch.core.IndexResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
public class ElasticSearchService implements SearchIndexService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ElasticSearchService.class);
    private final ElasticsearchClient esClient;

    @Autowired
    public ElasticSearchService(ElasticsearchClient esClient) {
        this.esClient = esClient;

        try {
            if (!esClient.indices().exists(
                    i -> i.index(ElasticSearchConfig.DOCUMENTS_INDEX_NAME)
            ).value()) {
                esClient.indices().create(c -> c
                        .index(ElasticSearchConfig.DOCUMENTS_INDEX_NAME)
                );
            }
            LOGGER.info("Created index " + ElasticSearchConfig.DOCUMENTS_INDEX_NAME);
        } catch (IOException e) {
            LOGGER.error("Error creating index \n" + e.getMessage());
        }
    }

    @Override
    public Result indexDocument(Document document) {
        // do indexing with ElasticSearch
        IndexResponse response = null;
        try {
            response = esClient.index(i -> i
                    .index(ElasticSearchConfig.DOCUMENTS_INDEX_NAME)
                    .id(String.valueOf(document.getId()))
                    .document(document)
            );
        } catch (IOException e) {
            LOGGER.error("Error indexing document \n" + e.getMessage());
        }
        assert response != null;

        String logMsg = "Indexed document " + document.getId() + document.getTitle() + ": result=" + response.result() + ", index=" + response.index();
        if ( response.result()!=Result.Created && response.result()!=Result.Updated )
            LOGGER.error("Failed to " + logMsg);
        else
            LOGGER.info(logMsg);

        return response.result();
    }

    @Override
    public Optional<Document> getDocumentById(int id) {
        try {
            GetResponse<Document> response = esClient.get(g -> g
                            .index(ElasticSearchConfig.DOCUMENTS_INDEX_NAME)
                            .id(String.valueOf(id)),
                    Document.class
            );
            return (response.found() && response.source()!=null) ? Optional.of(response.source()) : Optional.empty();
        } catch (IOException e) {
            LOGGER.error("Failed to get document id=" + id + " from elasticsearch: " + e);
            return Optional.empty();
        }
    }


}