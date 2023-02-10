package com.dangdiary.api.service;

import com.dangdiary.api.dto.mypage.MypageDTO;

public interface MypageService {
    MypageDTO getMypageView(int userId);
}
