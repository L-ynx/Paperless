package at.fhtw.swen3.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DocumentType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private long matchingAlgorithm;
    private boolean isInsensitive;

    @OneToMany(mappedBy = "documentType")
    private List<Document> documents;
}
