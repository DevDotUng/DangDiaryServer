package com.dangdiary.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dangdiary.api.dto.challengeDetail.ChallengeDetailDTO;
import com.dangdiary.api.service.ChallengeDetailService;

@RestController
@RequestMapping("/api/")
public class ChallengeDetailController {
    
    @Autowired
    ChallengeDetailService challengeDetailService;

    @GetMapping(value = "challengeDetail", produces = "application/json;charset=UTF-8")
    public ResponseEntity<ChallengeDetailDTO> home(int userId, int challengeId) {
        ChallengeDetailDTO challengeDetailDTO = challengeDetailService.getChallengeDetailView(userId, challengeId);
        return ResponseEntity.status(HttpStatus.OK).body(challengeDetailDTO);
    }
}
