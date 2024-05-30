package com.dangdiary.api.domain.users.service;

import com.dangdiary.api.domain.users.Users;
import com.dangdiary.api.domain.users.dto.UsersDto;
import com.dangdiary.api.domain.users.mapper.UsersMapper;
import com.dangdiary.api.domain.users.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsersService {

    private final UsersRepository usersRepository;
    private final UsersMapper usersMapper;

    public UsersDto getCurrentUser() {
        Users usser = usersRepository.findById(1L);
        UsersDto users = usersMapper.asDTO(usser);
        return users;

    }

}
