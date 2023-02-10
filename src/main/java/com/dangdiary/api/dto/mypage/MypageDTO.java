package com.dangdiary.api.dto.mypage;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class MypageDTO {
    public MypageDTO() {}
    private String userName;
    private String dogImage;
    private String dogName;
    private String dogBreed;
    private int dogAge;
    private String dogGender;
    private String dogBirth;
    
}
