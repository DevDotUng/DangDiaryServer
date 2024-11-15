package com.dangdiary.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dangdiary.api.dto.challenge.ChallengeDTO;
import com.dangdiary.api.service.ChallengeService;

@RestController
@RequestMapping("/api/")
public class ChallengeController {

    @Autowired
    ChallengeService challengeService;
    
    @GetMapping(value = "challenge", produces = "application/json;charset=UTF-8")
    public ResponseEntity<ChallengeDTO> home(int userId) {
        ChallengeDTO challengeDTO = challengeService.getChallengeView(userId);
        return ResponseEntity.status(HttpStatus.OK).body(challengeDTO);
    }
}