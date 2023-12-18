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
public class DocumentTypeDTO {
    private long id;
    private String name;
    private long matchingAlgorithm;
    private boolean isInsensitive;
    List<Document> documents;
}
