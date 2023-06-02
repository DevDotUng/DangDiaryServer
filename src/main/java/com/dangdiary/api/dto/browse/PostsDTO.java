package com.dangdiary.api.dto.browse;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PostsDTO {
    private String profileImage;
    private String dogName;
    private int userId;
    private int diaryId;
    private int challengeId;
    private String title;
    private String registerDate;
    private String weather;
    private String feeling;
    private String content;
    private String stickerImage;
    private String stickerShape;
    private Boolean isPublic;
    private int numberOfLike;
    private Boolean isLike;
    private List<String> images;
    private List<String> tags;
}
