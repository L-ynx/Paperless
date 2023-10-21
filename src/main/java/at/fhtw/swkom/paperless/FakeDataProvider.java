package at.fhtw.swkom.paperless;

import at.fhtw.swkom.paperless.services.dto.Correspondent;
import at.fhtw.swkom.paperless.services.dto.Document;
import org.openapitools.jackson.nullable.JsonNullable;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class FakeDataProvider {

    public Document getFakeDocument() {
        Document document = new Document();
        document.setId(1);
        document.setCorrespondent(JsonNullable.of(1));
        document.setDocumentType(JsonNullable.of(1));
        document.setTitle(JsonNullable.of("Sample Document"));
        document.setContent(JsonNullable.of("This is a sample document content"));
        List<Integer> tags = new ArrayList<>();
        tags.add(1);
        tags.add(2);
        document.setTags(JsonNullable.of(tags));
        document.setCreated(OffsetDateTime.now());
        document.setCreatedDate(OffsetDateTime.now());
        document.setModified(OffsetDateTime.now());
        document.setAdded(OffsetDateTime.now());
        document.setArchiveSerialNumber(JsonNullable.of("123456"));
        document.setOriginalFileName(JsonNullable.of("sample.pdf"));
        document.setArchivedFileName(JsonNullable.of("archived_sample.pdf"));

        return document;
    }

    public Correspondent getFakeCorrespondent() {
        Correspondent correspondent = new Correspondent();
        correspondent.setId(1L);
        correspondent.setSlug(JsonNullable.of("joben"));
        correspondent.setName(JsonNullable.of("joben"));
        correspondent.setMatch(JsonNullable.of("Example"));
        correspondent.setMatchingAlgorithm(1L);
        correspondent.setIsInsensitive(false);
        correspondent.setDocumentCount(10L);
        correspondent.setLastCorrespondence(OffsetDateTime.now());

        return correspondent;
    }
}