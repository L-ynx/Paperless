package at.fhtw.swen3.persistence.service.dto;

import at.fhtw.swen3.persistence.entity.Document;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Component
public class DocTagDTO {
    private long id;
    private String slug;
    private String name;
    private String color;
    private String match;
    private long matchingAlgorithm;
    private boolean isInsensitive;
    private boolean isInboxTag;
    private long documentCount;
    private List<Document> documents;
}
