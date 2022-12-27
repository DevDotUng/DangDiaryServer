package com.dangdiary.api.service;

import java.util.List;

import com.dangdiary.api.dto.browse.BrowseDTO;
import com.dangdiary.api.dto.browse.ImageDTO;

public interface BrowseService {
    BrowseDTO getSearchView();
    List<ImageDTO> getSearchView(String keyword);
}
