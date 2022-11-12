package com.dangdiary.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dangdiary.api.dao.HomeDAO;
import com.dangdiary.api.dto.home.HomeChallengeDTO;
import com.dangdiary.api.dto.home.HomeDTO;
import com.dangdiary.api.dto.home.HomeUserChallengeDTO;

@Service
public class HomeServiceImp implements HomeService {

    @Autowired
    HomeDAO homeDAO;

    @Override
    public HomeDTO getHomeView(int userId) {
        HomeDTO homeDTO = new HomeDTO();
        homeDTO.setHomeDogDTO(homeDAO.getHomeDog(userId));
        homeDTO.setHomeDiaryDTOs(homeDAO.getHomeDiary(userId));

        List<HomeUserChallengeDTO> homeUserChallengeDTOs = homeDAO.getHomeUserChallenge(userId);
        List<HomeChallengeDTO> homeChallengeDTOs = new ArrayList<HomeChallengeDTO>();

        for (HomeUserChallengeDTO homeUserChallengeDTO: homeUserChallengeDTOs) {

            List<HomeChallengeDTO> homeChallenges = homeDAO.getHomeChallenge(homeUserChallengeDTO.getChallengeId());

            for (HomeChallengeDTO homeChallenge: homeChallenges)
                homeChallengeDTOs.add(homeChallenge);
        }

        homeDTO.setHomeChallengeDTOs(homeChallengeDTOs);

        return homeDTO;
    }
    
}
