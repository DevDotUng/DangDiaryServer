package com.dangdiary.api.dto.login;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class KakaoAccountDTO {
    Boolean profile_nickname_needs_agreement;
    Boolean profile_image_needs_agreement;
    KakaoProfileDTO profile;
}
