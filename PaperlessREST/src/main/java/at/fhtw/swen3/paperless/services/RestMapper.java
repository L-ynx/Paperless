package at.fhtw.swen3.paperless.services;

import at.fhtw.swen3.paperless.services.dto.UserInfo;
import at.fhtw.swen3.persistence.service.dto.UserInfoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.openapitools.jackson.nullable.JsonNullable;

@Mapper(componentModel = "spring")
public interface RestMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "username", source = "username")
    @Mapping(target = "password", source = "password")
    UserInfoDTO toDTO(UserInfo userInfo);

    @Mapping(target = "username", source = "username")
    @Mapping(target = "password", source = "password")
    UserInfo toEntity(UserInfoDTO userInfoDTO);

    default String map(JsonNullable<String> value){
        return value.get();
    }

    default JsonNullable<String> map(String value){
        return JsonNullable.of(value);
    }
}
