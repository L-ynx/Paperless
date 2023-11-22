package at.fhtw.swen3.persistence.entity;

import lombok.*;

import jakarta.persistence.*;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Statistics {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private long documentsTotalCount;
    private long documentsInboxCount;
    private long inboxTagCount;
    private long characterCount;

    // TODO documentFileTypeCounts
    //private List<DocumentFileTypeCount> documentFileTypeCounts;
}
