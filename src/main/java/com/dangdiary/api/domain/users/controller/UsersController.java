package com.dangdiary.api.domain.users.controller;

import com.dangdiary.api.domain.users.dto.UsersWithDogsDTO;
import com.dangdiary.api.domain.users.request.DogResisterRequest;
import com.dangdiary.api.domain.users.service.UsersService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@Validated
@RequestMapping("/api/v2/users")
public class UsersController {

    private final UsersService usersService;

    @PostMapping("/info")
    public ResponseEntity<UsersWithDogsDTO> registerDogInfo(
        @RequestBody @Valid DogResisterRequest request) {
        return ResponseEntity.ok(usersService.registerDogInfo(request));
    }

}
