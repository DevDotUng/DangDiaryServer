package com.dangdiary.api.dto.sticker;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class StickerDTO {
    public StickerDTO() {};
    int numberOfOverdueDiary;
    List<StickerItemDTO> stickerItemDTOs;
}