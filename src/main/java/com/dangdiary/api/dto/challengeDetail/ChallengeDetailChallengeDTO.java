package com.dangdiary.api.dto.challengeDetail;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ChallengeDetailChallengeDTO {
    int challengeId;
    String category;
    String image;
    String title;
    String content;
    String authenticationMethod;
    String stickerImage;
}
