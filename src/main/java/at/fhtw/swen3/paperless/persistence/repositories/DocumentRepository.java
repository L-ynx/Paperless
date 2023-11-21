package at.fhtw.swen3.paperless.persistence.repositories;

import at.fhtw.swen3.paperless.persistence.entities.DocumentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends JpaRepository<DocumentEntity, Integer> {
}
