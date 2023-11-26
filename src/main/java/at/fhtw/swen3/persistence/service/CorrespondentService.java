package at.fhtw.swen3.persistence.service;

import at.fhtw.swen3.persistence.service.dto.CorrespondentDTO;

public interface CorrespondentService {
    CorrespondentDTO findById(Long id);
}
