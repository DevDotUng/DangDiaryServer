package com.dangdiary.api.domain.schedule.service;

import com.dangdiary.api.domain.schedule.service.impl.ScheduleServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduleService {

    @Autowired
    ScheduleServiceImp scheduleServiceImp;

    @Scheduled(cron = "0 0 10 * * *")
    public void updateDailyChallenges() {
        scheduleServiceImp.updateDailyChallenges();
    }
}
