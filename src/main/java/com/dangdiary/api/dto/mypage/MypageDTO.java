package com.dangdiary.api.dto.mypage;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MypageDTO {
    private String nickname;
    private String loginType;
    private String dogName;
    private String profileImage;
    private String breed;
    private String age;
    private String gender;
    private String birth;
    
}
