package com.dangdiary.api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ImagePathConfig implements WebMvcConfigurer {
    
    @Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		
		registry.addResourceHandler("/image/diary/**").addResourceLocations("file:/Users/ung/Documents/Spring/spring-boot-of-vs-code/src/main/webapp/static/diary/");

	}
}
