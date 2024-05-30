package com.dangdiary.api.domain.users.dto;

import com.dangdiary.api.domain.users.Dogs;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * DTO for {@link Dogs}
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DogsDto implements Serializable {

    Long dogId;
    String dogName;
    String profileImage;
    String breed;
    String gender;
    LocalDateTime birth;

}