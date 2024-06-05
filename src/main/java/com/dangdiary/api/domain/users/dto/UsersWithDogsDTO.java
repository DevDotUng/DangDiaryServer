package com.dangdiary.api.domain.users.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UsersWithDogsDTO extends UsersDto {

    DogsDto dogs;

}
