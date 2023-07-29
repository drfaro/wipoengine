package com.wipoengine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import java.io.IOException;
@SpringBootApplication()
public class WipoengineApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(WipoengineApplication.class);
	}
	public static void main(String[] args) throws IOException {
		SpringApplication.run(WipoengineApplication.class, args);
	}



}
