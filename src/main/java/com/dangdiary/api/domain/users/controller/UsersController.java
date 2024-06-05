package com.dangdiary.api.domain.users.controller;

import com.dangdiary.api.domain.users.dto.UsersWithDogsDTO;
import com.dangdiary.api.domain.users.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v2/users")
public class UsersController {

    private final UsersService usersService;

    @PostMapping("/info")
    public ResponseEntity<UsersWithDogsDTO> registerDogInfo() {
        usersService.getCurrentUser();
        return null;
    }

}
