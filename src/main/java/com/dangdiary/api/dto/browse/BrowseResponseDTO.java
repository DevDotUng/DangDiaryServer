package com.dangdiary.api.dto.browse;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class BrowseResponseDTO {
    List<String> autoCompleteWords;
    List<BrowseDTO> browses;
}
