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

    private String title;
    @Column(columnDefinition = "TEXT")
    private String content;
    private LocalDateTime createdAt;

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
}
