package at.fhtw.swen3.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull(message = "Filesize is required")
    private long filesize;

    @Column(columnDefinition = "TEXT")
    private String content;

    @NotNull(message = "Title is required")
    private String title;

    private String originalName;

    @NotNull(message = "Checksum is required")
    private String checksum;

    @NotNull(message = "MimeType is required")
    private String mimeType;

    @NotNull(message = "StoragePath is required")
    private String storagePath;

    @NotNull(message = "Owner is required")
    private String owner;

    @NotNull(message = "CreatedAt is required")
    private LocalDateTime createdAt;
    private LocalDateTime addedAt;
    private LocalDateTime modified;

    @ManyToOne
    @JoinColumn(name = "correspondent_id")
    private Correspondent correspondent;

    @ManyToOne
    @JoinColumn(name = "document_type_id")
    private DocumentType documentType;

    @ManyToMany
    private List<DocTag> docTags;
}
