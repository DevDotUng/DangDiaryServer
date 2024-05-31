package com.dangdiary.api.domain.users.request;

import com.google.firebase.database.annotations.NotNull;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
public class DogResisterRequest {

    @NotNull
    String nickname;
    @NotNull
    long userId;
    @NotBlank(message = "애완동물 이름은 필수값입니다.")
    String dogName;
    MultipartFile profileImage;
    @NotNull
    String birth;
    String breed;
    @NotNull
    String gender;

}
