package at.fhtw.swen3.persistence.mapper;

import at.fhtw.swen3.persistence.entity.DocumentType;
import at.fhtw.swen3.persistence.service.dto.DocumentTypeDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MapStructMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "matchingAlgorithm", source = "matchingAlgorithm")
    @Mapping(target = "isInsensitive", source = "insensitive")
    DocumentTypeDTO toDTO(DocumentType documentType);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "matchingAlgorithm", source = "matchingAlgorithm")
    @Mapping(target = "isInsensitive", source = "insensitive")
    @Mapping(target = "documents", ignore = true)
    DocumentType toEntity(DocumentTypeDTO documentTypeDTO);
}
