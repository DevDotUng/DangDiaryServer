package com.dangdiary.api.dto.challengeDetail;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ChallengeDetailTempDTO {
    private String image;
    private String title;
    private String content;
    private String authenticationMethod;
    private String stickerImage;
    private String stickerShape;
}
