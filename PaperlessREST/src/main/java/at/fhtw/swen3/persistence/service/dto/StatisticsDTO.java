package at.fhtw.swen3.persistence.service.dto;

import lombok.*;
import org.springframework.stereotype.Component;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Component
public class StatisticsDTO {
    private long id;
    private long documentsTotalCount;
    private long documentsInboxCount;
    private long inboxTagCount;
    private long characterCount;
}
