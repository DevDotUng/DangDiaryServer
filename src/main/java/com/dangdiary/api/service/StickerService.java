package com.dangdiary.api.service;

import com.dangdiary.api.dto.sticker.StickerResponseDTO;

public interface StickerService {
    StickerResponseDTO getStickerView(int userId);
}
