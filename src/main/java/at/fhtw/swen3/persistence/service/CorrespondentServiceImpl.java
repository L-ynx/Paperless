package at.fhtw.swen3.persistence.service;

import at.fhtw.swen3.persistence.entity.Correspondent;
import at.fhtw.swen3.persistence.mapper.DatabaseMapper;
import at.fhtw.swen3.persistence.repository.CorrespondentRepository;
import at.fhtw.swen3.persistence.service.dto.CorrespondentDTO;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CorrespondentServiceImpl implements CorrespondentService{
    private static final Logger LOGGER = LoggerFactory.getLogger(CorrespondentServiceImpl.class);
    private final CorrespondentRepository repository;
    private final DatabaseMapper mapper;

    public CorrespondentServiceImpl(CorrespondentRepository repository, DatabaseMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public CorrespondentDTO findById(Long id) {
        try {
            Correspondent correspondent = repository.findById(id).orElseThrow(EntityNotFoundException::new);
            LOGGER.info("Correspondent found: {}", correspondent);
            return mapper.toDTO(correspondent);
        } catch (EntityNotFoundException e) {
            LOGGER.error("Correspondent not found with id: {}", id);
            return null;
        }
    }
}
