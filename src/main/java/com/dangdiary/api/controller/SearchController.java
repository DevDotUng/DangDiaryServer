package com.dangdiary.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dangdiary.api.dto.search.ImageDTO;
import com.dangdiary.api.service.SearchService;

@RestController
@RequestMapping("/api/")
public class SearchController {
    
    @Autowired
    SearchService searchService;

    @GetMapping(value = "search", produces = "application/json;charset=UTF-8")
    public ResponseEntity<List<ImageDTO>> home() {
        List<ImageDTO> searchDTO = searchService.getSearchView();
        return ResponseEntity.status(HttpStatus.OK).body(searchDTO);
    }
}
