package at.fhtw.swen3.persistence.service.dto;

import at.fhtw.swen3.persistence.entity.Document;
import lombok.*;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Component
public class CorrespondentDTO {
    private long id;
    private long matchingAlgorithm;
    private boolean isInsensitive;
    private long documentCount;
    private OffsetDateTime lastCorrespondence;
    private List<Document> documents;
}
