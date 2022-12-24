package com.dangdiary.api.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.dangdiary.api.dto.search.ImageDTO;

@Mapper
public interface SearchDAO {
    List<ImageDTO> getImageDTOs();
}
