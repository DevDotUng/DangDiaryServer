package com.dangdiary.api.dto.challengeDetail;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ChallengeDetailDTO {
    private Boolean isChallenge;
    private String image;
    private String title;
    private String content;
    private String authenticationMethod;
    private String stickerImage;
    private String stickerShape;
    private int numberOfComplete;
    private String recommendDate;
    List<OtherChallengeDTO> otherDogChallenges;
}
