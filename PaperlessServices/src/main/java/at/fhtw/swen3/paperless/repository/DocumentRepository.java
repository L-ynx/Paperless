package at.fhtw.swen3.paperless.repository;

import at.fhtw.swen3.paperless.entity.Document;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {
    @Modifying
    @Transactional
    @Query("update Document d set d.content = ?1 where d.id = ?2")
    void updateContent(String content, long id);

}
