package at.fhtw.swen3.persistence.repository;

import at.fhtw.swen3.persistence.entity.Correspondent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CorrespondentRepository extends JpaRepository<Correspondent, Long> {
}
