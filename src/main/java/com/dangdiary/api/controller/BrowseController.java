package com.dangdiary.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dangdiary.api.dto.browse.BrowseDTO;
import com.dangdiary.api.dto.browse.ImageDTO;
import com.dangdiary.api.service.BrowseService;

@RestController
@RequestMapping("/api/")
public class BrowseController {
    
    @Autowired
    BrowseService browseService;

    @GetMapping(value = "browse", produces = "application/json;charset=UTF-8")
    public ResponseEntity<BrowseDTO> home() {
        BrowseDTO browseDTO = browseService.getSearchView();
        return ResponseEntity.status(HttpStatus.OK).body(browseDTO);
    }

    @GetMapping(value = "browse/search", produces = "application/json;charset=UTF-8")
    public ResponseEntity<List<ImageDTO>> search(String keyword) {
        List<ImageDTO> searchDTO = browseService.getSearchView(keyword);
        return ResponseEntity.status(HttpStatus.OK).body(searchDTO);
    }
}
