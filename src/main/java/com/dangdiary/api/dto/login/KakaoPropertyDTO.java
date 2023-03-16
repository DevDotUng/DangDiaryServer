package com.dangdiary.api.dto.login;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class KakaoPropertyDTO {
    String nickname;
    String profile_image;
    String thumbnail_image;
}
