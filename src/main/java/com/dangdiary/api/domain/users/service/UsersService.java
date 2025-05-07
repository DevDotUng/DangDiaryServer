package com.dangdiary.api.domain.users.service;

import com.dangdiary.api.domain.users.Dogs;
import com.dangdiary.api.domain.users.Users;
import com.dangdiary.api.domain.users.dto.UsersDto;
import com.dangdiary.api.domain.users.dto.UsersWithDogsDTO;
import com.dangdiary.api.domain.users.mapper.DogsMapper;
import com.dangdiary.api.domain.users.mapper.UsersMapper;
import com.dangdiary.api.domain.users.repository.DogsRepository;
import com.dangdiary.api.domain.users.repository.UsersRepository;
import com.dangdiary.api.domain.users.request.DogResisterRequest;
import jakarta.transaction.Transactional;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsersService {

    private final UsersRepository usersRepository;
    private final UsersMapper usersMapper;
    private final DogsMapper dogsMapper;
    private final DogsRepository dogsRepository;

    @Transactional
    public UsersWithDogsDTO registerDogInfo(DogResisterRequest request) {
        Users user = usersRepository.findById(request.getUserId());
        Dogs dog = Dogs.of(user, request.getDogName(), "test/image",
            request.getBreed(), request.getGender(), request.getBirth());
        user.modifyNickname(request.getNickname());

        dogsRepository.save(dog);
        UsersWithDogsDTO info = dogsMapper.asUsersWithDogsDTO(dog);
        return info;
    }

    public UsersDto getCurrentUser() {
        Users usser = usersRepository.findById(1L);
        Optional<Dogs> dogs = dogsRepository.findByUserId(1L);
        UsersDto users = usersMapper.asDTO(usser);
        return users;

    }

}
