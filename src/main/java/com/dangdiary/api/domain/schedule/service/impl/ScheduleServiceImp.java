package com.dangdiary.api.domain.schedule.service.impl;

import com.dangdiary.api.dao.ScheduleDAO;
import com.dangdiary.api.domain.schedule.dto.UserIdAndFirebaseTokenDTO;
import com.dangdiary.api.domain.schedule.dto.UserIdAndRecommendDateDTO;
import com.dangdiary.api.service.FirebaseCloudMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

@Service
public class ScheduleServiceImp {

    @Autowired
    ScheduleDAO scheduleDAO;

    @Autowired
    FirebaseCloudMessageService firebaseCloudMessageService;

    @Transactional
    public void updateDailyChallenges() throws IOException {
        Calendar cal = Calendar.getInstance();
        String format = "yyyy-MM-dd 10:00:00";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        cal.add(cal.DATE, +1);
        String date = sdf.format(cal.getTime());

        List<Integer> deleteDailyChallengeIds = scheduleDAO.getDeleteUserChallengeIds();
        if (!deleteDailyChallengeIds.isEmpty()) {
            scheduleDAO.deleteChallenges(deleteDailyChallengeIds);
        }
        List<UserIdAndFirebaseTokenDTO> userIdAndFirebaseTokens = scheduleDAO.getUserIdAndFirebaseTokens();

        for (UserIdAndFirebaseTokenDTO userIdAndFirebaseToken: userIdAndFirebaseTokens) {
            scheduleDAO.insertDailyChallengeByUserId(new UserIdAndRecommendDateDTO(userIdAndFirebaseToken.getUserId(), date));
            if (userIdAndFirebaseToken.getFirebaseToken() != null) {
                firebaseCloudMessageService.sendMessageTo(userIdAndFirebaseToken.getFirebaseToken(), "일일 챌린지 도착!", "일일 챌린지가 업데이트 되었어요! 지금 확인해보세요!!");
            }
        }
    }

    public void updateWeeklyChallenges() throws IOException {
        Calendar cal = Calendar.getInstance();
        String format = "yyyy-MM-dd 10:00:00";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        cal.add(cal.DATE, +7);
        String date = sdf.format(cal.getTime());

        List<Integer> deleteWeeklyChallengeIds = scheduleDAO.getDeleteWeeklyUserChallengeIds();
        if (!deleteWeeklyChallengeIds.isEmpty()) {
            scheduleDAO.deleteChallenges(deleteWeeklyChallengeIds);
        }
        List<UserIdAndFirebaseTokenDTO> userIdAndFirebaseTokens = scheduleDAO.getUserIdAndFirebaseTokens();

        for (UserIdAndFirebaseTokenDTO userIdAndFirebaseToken: userIdAndFirebaseTokens) {
            scheduleDAO.insertWeeklyChallengeByUserId(new UserIdAndRecommendDateDTO(userIdAndFirebaseToken.getUserId(), date));
            if (userIdAndFirebaseToken.getFirebaseToken() != null) {
                firebaseCloudMessageService.sendMessageTo(userIdAndFirebaseToken.getFirebaseToken(), "주간 챌린지 도착!", "주간 챌린지가 업데이트 되었어요! 지금 확인해보세요!!");
            }
        }
    }
}
