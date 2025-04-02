package com.dangdiary.api.controller;

import com.dangdiary.api.dto.challengeDetail.OverdueDiaryDTO;
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

    @GetMapping(value = "challengeDetail/start", produces = "application/json;charset=UTF-8")
    public ResponseEntity<Boolean> start(int userId, int challengeId) {
        Boolean isChallenge = challengeDetailService.startChallenge(userId, challengeId);
        return ResponseEntity.status(HttpStatus.OK).body(isChallenge);
    }

    @GetMapping(value = "challengeDetail/stop", produces = "application/json;charset=UTF-8")
    public ResponseEntity<Boolean> stop(int userId, int challengeId, String reason) {
        Boolean isChallenge = challengeDetailService.stopChallenge(userId, challengeId, reason);
        return ResponseEntity.status(HttpStatus.OK).body(isChallenge);
    }

    @GetMapping(value = "challengeDetail/end", produces = "application/json;charset=UTF-8")
    public ResponseEntity<OverdueDiaryDTO> end(int userId, int challengeId) {
        OverdueDiaryDTO overdueDiaryDTO = challengeDetailService.endChallenge(userId, challengeId);
        return ResponseEntity.status(HttpStatus.OK).body(overdueDiaryDTO);
    }
}
