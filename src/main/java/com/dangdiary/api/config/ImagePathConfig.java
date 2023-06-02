package com.dangdiary.api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ImagePathConfig implements WebMvcConfigurer {
    
    @Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {

		String path = "file:/Users/ung/Documents/Spring/DangDiaryServer/src/main/webapp/upload/";
		
		registry.addResourceHandler("/image/diary/**").addResourceLocations(path + "diary/");
		registry.addResourceHandler("/image/profile/**").addResourceLocations(path + "profile/");
		registry.addResourceHandler("/image/challenge/**").addResourceLocations(path + "challenge/");
		registry.addResourceHandler("/image/sticker/**").addResourceLocations(path + "sticker/");
		registry.addResourceHandler("/image/browse/**").addResourceLocations(path + "browse/");

	}
}
