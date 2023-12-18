package at.fhtw.swen3.persistence.repository;

import at.fhtw.swen3.persistence.entity.Statistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatisticsRepository extends JpaRepository<Statistics, Long> {
}
