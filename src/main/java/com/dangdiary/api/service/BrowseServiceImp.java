package com.dangdiary.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dangdiary.api.dao.BrowseDAO;
import com.dangdiary.api.dto.browse.ImageDTO;

@Service
public class BrowseServiceImp implements BrowseService {

    @Autowired
    BrowseDAO browseDAO;

    @Override
    public List<ImageDTO> getSearchView() {
        List<ImageDTO> imageDTOs = browseDAO.getImageDTOs();
        return imageDTOs;
    }

    @Override
    public List<ImageDTO> getSearchView(String keyword) {
        List<ImageDTO> imageDTOs = browseDAO.getImageDTOsByKeyword(keyword);
        return imageDTOs;
    }
    
}
