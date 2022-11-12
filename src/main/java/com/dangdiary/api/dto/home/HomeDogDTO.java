package com.dangdiary.api.dto.home;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class HomeDogDTO {
    private int dogId;
    private String dogName;
    private String profileImage;
}
