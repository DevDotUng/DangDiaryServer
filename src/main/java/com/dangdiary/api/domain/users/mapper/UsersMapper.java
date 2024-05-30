package com.dangdiary.api.domain.users.mapper;

import com.dangdiary.api.domain.users.Users;
import com.dangdiary.api.domain.users.dto.UsersDto;
import com.dangdiary.common.mapper.GenericMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

//componentModel="spring"을 통해서 spring container에 Bean으로 등록 해 준다.
//unmappedTargetPolicy IGNORE 만약, target class에 매핑되지 않는 필드가 있으면, null로 넣게 되고, 따로 report하지 않는다.
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UsersMapper extends GenericMapper<UsersDto, Users> {

}
