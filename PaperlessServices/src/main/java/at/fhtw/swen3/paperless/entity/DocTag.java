package at.fhtw.swen3.paperless.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DocTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String slug;
    private String name;
    private String color;
    private String match;
    private long matchingAlgorithm;
    private boolean isInsensitive;
    private boolean isInboxTag;
    private long documentCount;

    @ManyToMany
    private List<Document> documents;
}
