package com.dangdiary.api.dto.browse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CoverIdAndFirebaseTokenDTO {
    private int coverId;
    private String firebaseToken;
}
