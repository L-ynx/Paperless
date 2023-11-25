package at.fhtw.swen3.persistence.mapper;

import at.fhtw.swen3.persistence.entity.*;
import at.fhtw.swen3.persistence.service.dto.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DatabaseMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "matchingAlgorithm", source = "matchingAlgorithm")
    @Mapping(target = "isInsensitive", source = "insensitive")
    @Mapping(target = "documentCount", source = "documentCount")
    @Mapping(target = "lastCorrespondence", source = "lastCorrespondence")
    @Mapping(target = "documents", source = "documents")
    CorrespondentDTO toDTO(Correspondent correspondent);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "matchingAlgorithm", source = "matchingAlgorithm")
    @Mapping(target = "isInsensitive", source = "insensitive")
    @Mapping(target = "documentCount", source = "documentCount")
    @Mapping(target = "lastCorrespondence", source = "lastCorrespondence")
    @Mapping(target = "documents", source = "documents")
    Correspondent toEntity(CorrespondentDTO correspondentDTO);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "slug", source = "slug")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "color", source = "color")
    @Mapping(target = "match", source = "match")
    @Mapping(target = "matchingAlgorithm", source = "matchingAlgorithm")
    @Mapping(target = "isInsensitive", source = "insensitive")
    @Mapping(target = "isInboxTag", source = "inboxTag")
    @Mapping(target = "documentCount", source = "documentCount")
    @Mapping(target = "documents", source = "documents")
    DocTagDTO toDTO(DocTag docTag);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "slug", source = "slug")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "color", source = "color")
    @Mapping(target = "match", source = "match")
    @Mapping(target = "matchingAlgorithm", source = "matchingAlgorithm")
    @Mapping(target = "isInsensitive", source = "insensitive")
    @Mapping(target = "isInboxTag", source = "inboxTag")
    @Mapping(target = "documentCount", source = "documentCount")
    @Mapping(target = "documents", source = "documents")
    DocTag toEntity(DocTagDTO docTagDTO);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "title", source = "title")
    @Mapping(target = "content", source = "content")
    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "correspondent", source = "correspondent")
    @Mapping(target = "documentType", source = "documentType")
    @Mapping(target = "docTags", source = "docTags")
    DocumentDTO toDTO(Document document);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "title", source = "title")
    @Mapping(target = "content", source = "content")
    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "correspondent", source = "correspondent")
    @Mapping(target = "documentType", source = "documentType")
    @Mapping(target = "docTags", source = "docTags")
    Document toEntity(DocumentDTO documentDTO);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "matchingAlgorithm", source = "matchingAlgorithm")
    @Mapping(target = "isInsensitive", source = "insensitive")
    @Mapping(target = "documents", source = "documents")
    DocumentTypeDTO toDTO(DocumentType documentType);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "matchingAlgorithm", source = "matchingAlgorithm")
    @Mapping(target = "isInsensitive", source = "insensitive")
    @Mapping(target = "documents", source = "documents")
    DocumentType toEntity(DocumentTypeDTO documentTypeDTO);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "documentsTotalCount", source = "documentsTotalCount")
    @Mapping(target = "documentsInboxCount", source = "documentsInboxCount")
    @Mapping(target = "inboxTagCount", source = "inboxTagCount")
    @Mapping(target = "characterCount", source = "characterCount")
    StatisticsDTO toDTO(Statistics statistics);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "documentsTotalCount", source = "documentsTotalCount")
    @Mapping(target = "documentsInboxCount", source = "documentsInboxCount")
    @Mapping(target = "inboxTagCount", source = "inboxTagCount")
    @Mapping(target = "characterCount", source = "characterCount")
    Statistics toEntity(StatisticsDTO statisticsDTO);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "username", source = "username")
    @Mapping(target = "password", source = "password")
    UserInfoDTO toDTO(UserInfo user);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "username", source = "username")
    @Mapping(target = "password", source = "password")
    UserInfo toEntity(UserInfoDTO userDTO);

    List<DocumentDTO> toDTOs(List<Document> documents);

}
