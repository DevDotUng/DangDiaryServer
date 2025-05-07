package com.dangdiary.api.domain.users.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class UsersWithDogsDTO extends DogsDto {

    UsersDto users;

}
