package com.dangdiary.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dangdiary.api.dto.lookAround.LookAroundDTO;
import com.dangdiary.api.service.LookAroundService;


@RestController
@RequestMapping("/api/")
public class LookAroundController {

    @Autowired
    LookAroundService lookAroundService;

    @GetMapping(value="lookAround", produces="application/json;charset=UTF-8")
    public ResponseEntity<LookAroundDTO> lookAround() {
        LookAroundDTO lookAroundDTO = lookAroundService.getLookAroundView();
        return ResponseEntity.status(HttpStatus.OK).body(lookAroundDTO);
    }
}
