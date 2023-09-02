package com.dangdiary.api.domain.schedule.service.impl;

import com.dangdiary.api.dao.ScheduleDAO;
import com.dangdiary.api.domain.schedule.dto.UserIdAndRecommendDateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

@Service
public class ScheduleServiceImp {

    @Autowired
    ScheduleDAO scheduleDAO;

    @Transactional
    public void updateDailyChallenges() {
        Calendar cal = Calendar.getInstance();
        String format = "yyyy-MM-dd 10:00:00";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        cal.add(cal.DATE, +1);
        String date = sdf.format(cal.getTime());

        List<Integer> deleteDailyChallengeIds = scheduleDAO.getDeleteUserChallengeIds();
        if (!deleteDailyChallengeIds.isEmpty()) {
            scheduleDAO.deleteDailyChallenges(deleteDailyChallengeIds);
        }
        List<Integer> userIds = scheduleDAO.getUserIds();

        for (Integer userId: userIds) {
            scheduleDAO.insertDailyChallengeByUserId(new UserIdAndRecommendDateDTO(userId, date));
        }
    }
}
