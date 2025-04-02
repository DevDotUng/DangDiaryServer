package com.dangdiary.api.domain.sticker.service;

import com.dangdiary.api.domain.sticker.dto.StickerDetailResponseDTO;
import com.dangdiary.api.domain.sticker.dto.StickerResponseDTO;

public interface StickerService {
    StickerResponseDTO getStickerView(int userId);
    StickerDetailResponseDTO getStickerDetailView(int userId, int challengeId);
}
