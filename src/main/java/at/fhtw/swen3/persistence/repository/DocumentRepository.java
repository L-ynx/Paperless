package at.fhtw.swen3.persistence.repository;

import at.fhtw.swen3.persistence.entity.Document;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {
}
