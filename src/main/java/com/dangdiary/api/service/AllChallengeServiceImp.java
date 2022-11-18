package com.dangdiary.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dangdiary.api.dao.AllChallengeDAO;
import com.dangdiary.api.dto.allChallenge.AllChallengeDTO;
import com.dangdiary.api.dto.allChallenge.ChallengeDTO;
import com.dangdiary.api.dto.allChallenge.ChallengeTempDTO;
import com.dangdiary.api.dto.allChallenge.OverdueDiaryDTO;

@Service
public class AllChallengeServiceImp implements AllChallengeService {

    @Autowired
    AllChallengeDAO allChallengeDAO;

    @Override
    public AllChallengeDTO getAllChallengeView(int userId) {

        AllChallengeDTO allChallengeDTO = new AllChallengeDTO();

        List<ChallengeTempDTO> challengeTempDTOs = allChallengeDAO.getChallengeTempDTO(userId);
        List<ChallengeDTO> challengeDTOs = new ArrayList<ChallengeDTO>();

        for (ChallengeTempDTO challengeTempDTO: challengeTempDTOs) {

            ChallengeDTO challengeDTO = new ChallengeDTO(
                challengeTempDTO.getChallengeId(),
                challengeTempDTO.getCategory(),
                challengeTempDTO.getTitle(),
                challengeTempDTO.getImage(),
                getStatus(challengeTempDTO));
            challengeDTOs.add(challengeDTO);
        }

        List<OverdueDiaryDTO> overdueDiaryDTOs = allChallengeDAO.getOverdueDiaryDTO(userId);

        allChallengeDTO.setOverdueDiaryDTOs(overdueDiaryDTOs);
        allChallengeDTO.setChallengeDTOs(challengeDTOs);

        return allChallengeDTO;
    }
    
    private String getStatus(ChallengeTempDTO challengeTempDTO) {

        String status;

        if (challengeTempDTO.getStartDate() == null)
            status = "none";
        else if (challengeTempDTO.getEndDate() == null)
            status = "inProgress";
        else if (challengeTempDTO.getDiaryBool() == null)
            status = "overdue";
        else
            status = "complete";

        return status;
    }
}
