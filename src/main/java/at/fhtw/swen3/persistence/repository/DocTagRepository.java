package at.fhtw.swen3.persistence.repository;

import at.fhtw.swen3.persistence.entity.DocTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocTagRepository extends JpaRepository<DocTag, Long> {
}
