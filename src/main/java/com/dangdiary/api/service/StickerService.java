package com.dangdiary.api.service;

import com.dangdiary.api.dto.sticker.StickerDetailResponseDTO;
import com.dangdiary.api.dto.sticker.StickerResponseDTO;

public interface StickerService {
    StickerResponseDTO getStickerView(int userId);
    StickerDetailResponseDTO getStickerDetailView(int userId, int challengeId);
}
