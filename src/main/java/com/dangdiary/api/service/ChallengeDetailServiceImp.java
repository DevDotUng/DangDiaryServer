package com.dangdiary.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dangdiary.api.dao.ChallengeDetailDAO;
import com.dangdiary.api.dto.challengeDetail.ChallengeDetailDTO;
import com.dangdiary.api.dto.challengeDetail.OtherDogChallengeDTO;

@Service
public class ChallengeDetailServiceImp implements ChallengeDetailService {
    
    @Autowired
    ChallengeDetailDAO challengeDetailDAO;

    @Override
    public ChallengeDetailDTO getChallengeDetailView(int challengeId) {
        ChallengeDetailDTO challengeDetailDTO = new ChallengeDetailDTO();
        challengeDetailDTO.setChallengeDetailChallengeDTO(challengeDetailDAO.getChallengeDetailDTO(challengeId));
        challengeDetailDTO.setNumOfComplete(challengeDetailDAO.getNumOfComplete(challengeId));
        List<Integer> otherDiaryIds = challengeDetailDAO.getOtherDiaryIds(challengeId);

        List<OtherDogChallengeDTO> homeChallengeDTOs = new ArrayList<OtherDogChallengeDTO>();

        for (int otherDiaryId: otherDiaryIds) {
            homeChallengeDTOs.add(challengeDetailDAO.getOtherDogChallenge(otherDiaryId));
        }
        challengeDetailDTO.setOtherDogChallenges(homeChallengeDTOs);

        return challengeDetailDTO;
    }
}
