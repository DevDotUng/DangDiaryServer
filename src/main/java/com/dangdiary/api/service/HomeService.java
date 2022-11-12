package com.dangdiary.api.service;

import com.dangdiary.api.dto.home.HomeDTO;

public interface HomeService {
    HomeDTO getHomeView(int userId);
}