package com.dangdiary.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dangdiary.api.dto.doChallenge.UserChallengeDTO;
import com.dangdiary.api.service.CompleteChallengeService;

@RestController
@RequestMapping("/api/")
public class CompleteChallengeController {

    @Autowired
    CompleteChallengeService completeChallengeService;
    
    @GetMapping(value = "completeChallenge", produces = "application/json;charset=UTF-8")
    public ResponseEntity<UserChallengeDTO> home(int userId, int challengeId) {

        UserChallengeDTO userChallengeDTO = completeChallengeService.completeChallenge(userId, challengeId);

        return ResponseEntity.status(HttpStatus.OK).body(userChallengeDTO);
    }
}