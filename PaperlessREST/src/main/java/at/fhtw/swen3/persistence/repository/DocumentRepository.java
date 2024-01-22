package at.fhtw.swen3.persistence.repository;

import at.fhtw.swen3.persistence.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository(value = "restDocumentRepository")
public interface DocumentRepository extends JpaRepository<Document, Long> {
}
