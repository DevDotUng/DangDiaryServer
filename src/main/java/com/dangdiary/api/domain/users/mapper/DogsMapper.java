package com.dangdiary.api.domain.users.mapper;

import com.dangdiary.api.domain.users.Dogs;
import com.dangdiary.api.domain.users.dto.DogsDto;
import com.dangdiary.api.domain.users.dto.UsersWithDogsDTO;
import com.dangdiary.common.mapper.GenericMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DogsMapper extends GenericMapper<DogsDto, Dogs> {

    @Mapping(target = "users", source = "dogs.users")
    UsersWithDogsDTO asUsersWithDogsDTO(Dogs dogs);

}
