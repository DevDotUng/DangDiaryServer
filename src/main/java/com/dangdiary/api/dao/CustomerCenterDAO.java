package com.dangdiary.api.dao;

import java.util.List;

import com.dangdiary.api.dto.customerCenter.InquiryDTO;
import com.dangdiary.api.dto.customerCenter.InquiryHistoryDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.dangdiary.api.dto.customerCenter.FAQDTO;
import com.dangdiary.api.dto.customerCenter.NoticeDTO;

@Mapper
public interface CustomerCenterDAO {
    List<NoticeDTO> getNoticeDTOs();
    List<FAQDTO> getFAQDTOs();
    void postNotice(@Param("title") String title, @Param("content") String content);
    NoticeDTO getNotice();
    void inquiry(InquiryDTO inquiryDTO);
    List<InquiryHistoryDTO> getInquiryHistoryDTOList(int userId);
    void likeInquiry(int inquiryId, int isLike);
}
