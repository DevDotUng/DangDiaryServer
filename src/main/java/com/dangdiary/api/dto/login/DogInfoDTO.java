package com.dangdiary.api.dto.login;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class DogInfoDTO {
    int userId;
    String nickname;
    String dogName;
    String profileImage;
    String breed;
    String birth;
    String gender;
}
