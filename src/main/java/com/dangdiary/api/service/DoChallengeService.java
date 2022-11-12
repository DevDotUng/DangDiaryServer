package com.dangdiary.api.service;

import com.dangdiary.api.dto.doChallenge.DoChallengeDTO;
import com.dangdiary.api.dto.doChallenge.UserChallengeDTO;

public interface DoChallengeService {
    UserChallengeDTO postDoChallenge(DoChallengeDTO doChallengeDTO);
}
