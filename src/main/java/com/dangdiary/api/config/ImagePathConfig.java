package com.dangdiary.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ImagePathConfig implements WebMvcConfigurer {

	@Autowired
	private Environment env;
    
    @Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {

		String path = env.getProperty("image.path");
		
		registry.addResourceHandler("/image/diary/**").addResourceLocations(path + "diary/");
		registry.addResourceHandler("/image/profile/**").addResourceLocations(path + "profile/");
		registry.addResourceHandler("/image/challenge/**").addResourceLocations(path + "challenge/");
		registry.addResourceHandler("/image/sticker/**").addResourceLocations(path + "sticker/");
		registry.addResourceHandler("/image/browse/**").addResourceLocations(path + "browse/");

	}
}
