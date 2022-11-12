package com.dangdiary.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dangdiary.api.dto.home.HomeDTO;
import com.dangdiary.api.service.HomeService;

@RestController
@RequestMapping("/api/")
public class HomeController {

    @Autowired
    HomeService homeService;
    
    @GetMapping(value = "home", produces = "application/json;charset=UTF-8")
    public ResponseEntity<HomeDTO> home(int userId) {
        HomeDTO homeDTO = homeService.getHomeView(userId);
        return ResponseEntity.status(HttpStatus.OK).body(homeDTO);
    }
}