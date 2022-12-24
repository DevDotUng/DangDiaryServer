package com.dangdiary.api.service;

import java.util.List;

import com.dangdiary.api.dto.search.ImageDTO;

public interface SearchService {
    List<ImageDTO> getSearchView();
}
