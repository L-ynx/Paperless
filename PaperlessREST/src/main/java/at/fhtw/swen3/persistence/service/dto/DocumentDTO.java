package at.fhtw.swen3.persistence.service.dto;

import at.fhtw.swen3.persistence.entity.Correspondent;
import at.fhtw.swen3.persistence.entity.DocTag;
import at.fhtw.swen3.persistence.entity.DocumentType;
import lombok.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Component
public class DocumentDTO {
    private long id;
    private long filesize;

    private String title;
    private String originalName;
    private String content;
    private String checksum;
    private String mimeType;
    private String storagePath;
    private String owner;

    private LocalDateTime createdAt;
    private LocalDateTime addedAt;
    private LocalDateTime modified;

    private Correspondent correspondent;

    private DocumentType documentType;

    private List<DocTag> docTags;
}
