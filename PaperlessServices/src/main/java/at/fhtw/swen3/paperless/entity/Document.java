package at.fhtw.swen3.paperless.entity;

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

    // manually added getter & setter (annotations in ElasticSearchService did not work)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    // Manually added setter for content
    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
