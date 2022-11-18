package com.dangdiary.api.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.dangdiary.api.dto.allChallenge.ChallengeTempDTO;
import com.dangdiary.api.dto.allChallenge.OverdueDiaryDTO;

@Mapper
public interface AllChallengeDAO {
    List<OverdueDiaryDTO> getOverdueDiaryDTO(int userId);
    List<ChallengeTempDTO> getChallengeTempDTO(int userId);
}
