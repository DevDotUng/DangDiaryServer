package com.dangdiary.api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ImagePathConfig implements WebMvcConfigurer {
    
    @Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		
		registry.addResourceHandler("/image/diary/**").addResourceLocations("file:/Users/ung/Documents/Spring/DangDiaryServer/src/main/webapp/upload/diary/");
		registry.addResourceHandler("/image/profile/**").addResourceLocations("file:/Users/ung/Documents/Spring/DangDiaryServer/src/main/webapp/upload/profile/");
		registry.addResourceHandler("/image/challenge/**").addResourceLocations("file:/Users/ung/Documents/Spring/DangDiaryServer/src/main/webapp/upload/challenge/");
		registry.addResourceHandler("/image/sticker/**").addResourceLocations("file:/Users/ung/Documents/Spring/DangDiaryServer/src/main/webapp/upload/sticker/");

	}
}
