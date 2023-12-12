package at.fhtw.swen3.persistence.service;

import at.fhtw.swen3.persistence.entity.DocTag;
import at.fhtw.swen3.persistence.mapper.DatabaseMapper;
import at.fhtw.swen3.persistence.repository.DocTagRepository;
import at.fhtw.swen3.persistence.service.dto.DocTagDTO;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class DocTagServiceImpl implements DocTagService{
    private static final Logger LOGGER = LoggerFactory.getLogger(DocTagServiceImpl.class);
    private final DocTagRepository repository;
    private final DatabaseMapper mapper;

    @Autowired
    public DocTagServiceImpl(DocTagRepository repository, DatabaseMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<DocTagDTO> findAllById(List<Long> collect) {
        try {
            if (collect == null || collect.isEmpty()) throw new EntityNotFoundException();
            List<DocTag> docTags = repository.findAllById(collect);
            LOGGER.info("DocTags found: {}", docTags);
            return mapper.toDocTagDTOs(docTags);
        } catch (Exception e) {
            LOGGER.warn("DocTags not found with ids: {}", collect);
            return null;
        }
    }
}
