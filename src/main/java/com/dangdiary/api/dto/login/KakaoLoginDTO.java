package com.dangdiary.api.dto.login;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class KakaoLoginDTO {
    int userId = 0;
    long id;
    String name;
    String refreshToken;
}
