package com.dangdiary.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dangdiary.api.dao.LookAroundDAO;
import com.dangdiary.api.dto.lookAround.LikeChallengeDTO;
import com.dangdiary.api.dto.lookAround.ViewChallengeDTO;
import com.dangdiary.api.dto.lookAround.BreedChallengeDTO;
import com.dangdiary.api.dto.lookAround.LookAroundDTO;

@Service
public class LookAroundServiceImp implements LookAroundService {
    @Autowired
    LookAroundDAO lookAroundDAO;

    @Override
    public LookAroundDTO getLookAroundView() {
        LookAroundDTO lookAroundDTO = new LookAroundDTO();

        List<LikeChallengeDTO> likeChallengeDTOs = lookAroundDAO.getLikeChallengeDTOs();
        List<ViewChallengeDTO> viewChallengeDTOs = lookAroundDAO.getViewChallengeDTOs();
        List<BreedChallengeDTO> breedChallengeDTOs = lookAroundDAO.getBreedChallengeDTOs();

        lookAroundDTO.setLikeChallengeDTOs(likeChallengeDTOs);
        lookAroundDTO.setViewChallengeDTOs(viewChallengeDTOs);
        lookAroundDTO.setBreedChallengeDTOs(breedChallengeDTOs);

        return lookAroundDTO;
    
    }

}
