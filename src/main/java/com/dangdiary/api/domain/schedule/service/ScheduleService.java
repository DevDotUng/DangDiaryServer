package com.dangdiary.api.domain.schedule.service;

import com.dangdiary.api.domain.schedule.service.impl.ScheduleServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ScheduleService {

    @Autowired
    ScheduleServiceImp scheduleServiceImp;

    @Scheduled(cron = "0 0 10 * * *")
    public void updateDailyChallenges() throws IOException {
        scheduleServiceImp.updateDailyChallenges();
    }

    @Scheduled(cron = "0 0 10 * * 1")
    public void updateWeeklyChallenges() throws IOException {
        scheduleServiceImp.updateWeeklyChallenges();
    }
}
