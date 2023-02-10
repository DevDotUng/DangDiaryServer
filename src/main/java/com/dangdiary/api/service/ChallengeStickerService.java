package com.dangdiary.api.service;

import com.dangdiary.api.dto.challengeSticker.ChallengeStickerDTO;

public interface ChallengeStickerService {
    ChallengeStickerDTO getChallengeStickerView(int userId, int challengeId);
}
