package com.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;


@Configuration
public class WebConfig {

	@Bean
	public WebMvcConfigurer corsConfig() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registory) {
				registory.addMapping("/**").allowedOrigins("http://localhost:5173/")
						.allowedMethods(HttpMethod.GET.name(), HttpMethod.POST.name(), HttpMethod.PUT.name(),
								HttpMethod.PATCH.name(), HttpMethod.DELETE.name())
						.allowedHeaders(HttpHeaders.CONTENT_TYPE,
								HttpHeaders.AUTHORIZATION);
			}
		};
	}
}
