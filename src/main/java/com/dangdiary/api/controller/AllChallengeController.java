package com.dangdiary.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dangdiary.api.dto.allChallenge.AllChallengeDTO;
import com.dangdiary.api.service.AllChallengeService;

@RestController
@RequestMapping("/api/")
public class AllChallengeController {
    
    @Autowired
    AllChallengeService allChallengeService;

    @GetMapping(value = "allChallenge", produces = "application/json;charset=UTF-8")
    public ResponseEntity<AllChallengeDTO> home(int userId) {

        AllChallengeDTO allChallengeDTO = allChallengeService.getAllChallengeView(userId);

        return ResponseEntity.status(HttpStatus.OK).body(allChallengeDTO);
    }
}
