package at.fhtw.swen3.persistence.service;

import at.fhtw.swen3.persistence.service.dto.DocTagDTO;

import java.util.List;

public interface DocTagService {
    List<DocTagDTO> findAllById(List<Long> collect);
}
