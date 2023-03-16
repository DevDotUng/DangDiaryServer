package com.dangdiary.api.dto.login;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class KakaoProfileDTO {
    String nickname;
    String thumbnail_image_url;
    String profile_image_url;
    Boolean is_default_image;
}