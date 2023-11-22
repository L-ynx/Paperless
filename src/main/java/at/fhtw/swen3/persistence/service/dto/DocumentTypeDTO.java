package at.fhtw.swen3.persistence.service.dto;

import lombok.*;
import org.springframework.stereotype.Component;

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
}
