package com.dangdiary.api.dto.writeDiary;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ImageOrTagDTO {
    private int diaryId;
    private int index;
    private String imageOrTag;
}
