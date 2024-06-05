package com.dangdiary.api.domain.users.request;

import com.google.firebase.database.annotations.NotNull;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
public class DogResisterRequest {

    @NotBlank(message = "닉네임은 필수값입니다.")
    String nickname;
    @NotNull
    long userId;
    @NotBlank(message = "애완동물 이름은 필수값입니다.")
    String dogName;
    MultipartFile profileImage;
    @NotBlank(message = "생년월일은 필수값입니다.")
    String birth;
    String breed;
    @NotBlank(message = "성별은 필수값입니다(수컷/암컷)")
    String gender;

}
