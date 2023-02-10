package com.dangdiary.api.dto.myDiary;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class MyDiaryByCoverDTO {
    private int coverId;
    private String date;
    private String coverTitle;
    private String coverColor;
    private String holderColor;
    private int numberOfLike;
    List<MyDiaryEachDTO> diary;

    public void addMyDiaryEachDTO(MyDiaryEachDTO myDiaryEachDTO) {
        diary.add(myDiaryEachDTO);
    }
    public void addNumberOfLike(int like) {
        numberOfLike += like;
    }
}
