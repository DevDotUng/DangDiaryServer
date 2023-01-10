package com.dangdiary.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dangdiary.api.dao.HomeDAO;
import com.dangdiary.api.dto.home.HomeInProgressChallengeDTO;
import com.dangdiary.api.dto.home.HomeRecommendChallengeDTO;
import com.dangdiary.api.dto.home.HomeDTO;

@Service
public class HomeServiceImp implements HomeService {

    @Autowired
    HomeDAO homeDAO;

    @Override
    public HomeDTO getHomeView(int userId) {
        HomeDTO homeDTO = homeDAO.getHomeDTO(userId);

        List<HomeInProgressChallengeDTO> homeInProgressChallengeDTOs = homeDAO.getHomeInProgressChallengeDTOs(userId);
        List<HomeRecommendChallengeDTO> homeRecommendChallengeDTOs = homeDAO.getHomeRecommendChallengeDTOs(userId);

        homeDTO.setInProgressChallenges(homeInProgressChallengeDTOs);
        homeDTO.setRecommendChallenges(homeRecommendChallengeDTOs);

        return homeDTO;
    }
    
}
