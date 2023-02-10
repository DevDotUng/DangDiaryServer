package com.dangdiary.api.dto.challengeSticker;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ChallengeDiaryDTO {
    public ChallengeDiaryDTO() {}
    private String diaryDate;
    private String diaryTitle;
    private int numOfLike;
    
}
