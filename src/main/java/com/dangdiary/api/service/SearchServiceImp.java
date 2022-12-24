package com.dangdiary.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dangdiary.api.dao.SearchDAO;
import com.dangdiary.api.dto.search.ImageDTO;

@Service
public class SearchServiceImp implements SearchService {

    @Autowired
    SearchDAO searchDAO;

    @Override
    public List<ImageDTO> getSearchView() {
        List<ImageDTO> imageDTOs = searchDAO.getImageDTOs();
        return imageDTOs;
    }
    
}
