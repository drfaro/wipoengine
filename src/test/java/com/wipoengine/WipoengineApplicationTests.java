package com.wipoengine;

import com.wipoengine.controller.IndexController;
import com.wipoengine.dtos.ProcessRecordDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.Duration;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class WipoengineApplicationTests {

	@Autowired
	IndexController indexController;

	@Test
	public void contextLoads() {
		Assertions.assertThat(indexController).isNotNull();
	}

}
