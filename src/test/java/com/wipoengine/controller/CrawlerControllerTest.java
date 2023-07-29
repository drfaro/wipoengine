package com.wipoengine.controller;

import com.wipoengine.dtos.ProcessRecordDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class CrawlerControllerTest {


    @Autowired
    CrawlerController crawlerController;

    public WebDriver requestSuccess(String idProcess){

        String url = "https://patentscope.wipo.int/search/pt/detail.jsf?docId="+idProcess+"&redirectedID=true";
        ProcessRecordDto processRecordDto;
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");

        WebDriver driver = new ChromeDriver(options);
        driver.get(url);
        return driver;
    }
    @Test
    void crawlerHttpSuccess() {

        int httpStatusCode = 0;
        var driver = requestSuccess("WO2002008676");
        try {

            HttpURLConnection cn = (HttpURLConnection) new URL(driver.getCurrentUrl()).openConnection();

            cn.setRequestMethod("HEAD");
            cn.connect();
            httpStatusCode = cn.getResponseCode();

        } catch (Exception e) {
            assertEquals(200,httpStatusCode);
        }

        assertEquals(200,httpStatusCode);

    }

    @Test
    void crawlerDataSuccess() throws Exception {
        ResponseEntity<Object> crawlerRequest = crawlerController.crawler("WO2002008676");
        assertEquals(200,crawlerRequest.getStatusCode().value());
        assertNotNull(crawlerRequest.getBody());
    }

    @Test
    void crawlerDataFail() throws Exception {
        ResponseEntity<Object> crawlerRequest = crawlerController.crawler("AAAAAAAAA");
        assertEquals(404,crawlerRequest.getStatusCode().value());
        assertEquals("null",crawlerRequest.getBody());
    }
}