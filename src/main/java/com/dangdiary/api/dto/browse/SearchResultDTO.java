package com.dangdiary.api.dto.browse;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class SearchResultDTO {
    List<String> hashTags;
    List<AccountDTO> accounts;
    List<String> breeds;
}
