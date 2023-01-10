package com.dangdiary.api.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.dangdiary.api.dto.customerCenter.FAQDTO;
import com.dangdiary.api.dto.customerCenter.NoticeDTO;

@Mapper
public interface CustomerCenterDAO {
    List<NoticeDTO> getNoticeDTOs();
    List<FAQDTO> getFAQDTOs();
}