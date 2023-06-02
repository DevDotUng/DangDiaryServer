package com.dangdiary.api.dto.browse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class BrowseDTO {
    private int browseId;
    private String title;
    private String content;
    private String image;
    private String query;
}
