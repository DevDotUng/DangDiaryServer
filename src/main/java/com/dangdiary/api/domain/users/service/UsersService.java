package com.dangdiary.api.domain.users.service;

import com.dangdiary.api.domain.users.Users;
import com.dangdiary.api.domain.users.dto.UsersDto;
import com.dangdiary.api.domain.users.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsersService {

    private final UsersRepository usersRepository;

    public UsersDto getCurrentUser() {
        UsersDto usersDto = new UsersDto();
        Users users = usersRepository.findById(1L);
        return null;

    }

}
