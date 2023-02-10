package com.dangdiary.api.dto.lookAround;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@AllArgsConstructor
@Getter
@Setter

public class LikeChallengeDTO {
    private int diaryId;
    private int challengeId;
    private int userId;
    private String dogImage;
    private String stickerImage;
    private String title;
    private String register_date;
    private String weather;
    private String feeling;
    private String image1;
    private String image2;
    private String image3;
    private String image4;
    private String image5;
    private int numberOfLike;
    private String content;
    private String hashtag1;


}
