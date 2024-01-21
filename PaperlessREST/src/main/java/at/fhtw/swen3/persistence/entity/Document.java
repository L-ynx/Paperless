package at.fhtw.swen3.persistence.entity;

import jakarta.persistence.*;
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
    private long filesize;

    @Column(columnDefinition = "TEXT")
    private String content;
    private String title;
    private String originalName;
    private String checksum;
    private String mimeType;
    private String storagePath;
    private String owner;

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
