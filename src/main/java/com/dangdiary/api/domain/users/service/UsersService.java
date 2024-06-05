package com.dangdiary.api.domain.users.service;

import com.dangdiary.api.domain.users.Dogs;
import com.dangdiary.api.domain.users.Users;
import com.dangdiary.api.domain.users.dto.UsersDto;
import com.dangdiary.api.domain.users.mapper.UsersMapper;
import com.dangdiary.api.domain.users.repository.DogsRepository;
import com.dangdiary.api.domain.users.repository.UsersRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsersService {

    private final UsersRepository usersRepository;
    private final UsersMapper usersMapper;
    private final DogsRepository dogsRepository;

    public UsersDto getCurrentUser() {
        Users usser = usersRepository.findById(1L);
        Optional<Dogs> dogs = dogsRepository.findByUserId(1L);
        UsersDto users = usersMapper.asDTO(usser);
        return users;

    }

}
