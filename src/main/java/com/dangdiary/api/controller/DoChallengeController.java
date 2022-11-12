package com.dangdiary.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dangdiary.api.dto.doChallenge.DoChallengeDTO;
import com.dangdiary.api.dto.doChallenge.UserChallengeDTO;
import com.dangdiary.api.service.DoChallengeService;

@RestController
@RequestMapping("/api/")
public class DoChallengeController {

    @Autowired
    DoChallengeService doChallengeService;
    
    @PostMapping("doChallenge")
    public ResponseEntity<UserChallengeDTO> home(@RequestBody DoChallengeDTO userChallenge) {

        UserChallengeDTO doChallengeDTO = doChallengeService.postDoChallenge(userChallenge);

        return ResponseEntity.status(HttpStatus.OK).body(doChallengeDTO);
    }
}