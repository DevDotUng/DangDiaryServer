package com.dangdiary.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dangdiary.api.dao.BrowseDAO;
import com.dangdiary.api.dto.browse.BrowseDTO;
import com.dangdiary.api.dto.browse.ImageDTO;

@Service
public class BrowseServiceImp implements BrowseService {

    @Autowired
    BrowseDAO browseDAO;

    @Override
    public BrowseDTO getSearchView() {
        List<String> autoCompleteWords = browseDAO.getAutoCompleteWords();
        List<ImageDTO> imageDTOs = browseDAO.getImageDTOs();
        
        BrowseDTO browseDTO = new BrowseDTO(autoCompleteWords, imageDTOs);

        return browseDTO;
    }

    @Override
    public List<ImageDTO> getSearchView(String keyword) {
        List<ImageDTO> imageDTOs = browseDAO.getImageDTOsByKeyword(keyword);
        return imageDTOs;
    }
    
}
