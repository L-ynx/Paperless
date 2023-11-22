package at.fhtw.swen3.persistence.entity;

import lombok.*;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DocTag {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
