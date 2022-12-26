package com.dangdiary.api.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.dangdiary.api.dto.browse.ImageDTO;

@Mapper
public interface BrowseDAO {
    List<ImageDTO> getImageDTOs();
    List<ImageDTO> getImageDTOsByKeyword(String keyword);
}
