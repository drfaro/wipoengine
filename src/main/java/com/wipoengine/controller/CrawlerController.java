package com.wipoengine.controller;

import com.wipoengine.dtos.ProcessRecordDto;
import com.wipoengine.models.ProcessModel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class CrawlerController {

    @GetMapping("/crawler/{id}")
    public ResponseEntity<Object> crawler(@PathVariable(value="id") String id) throws Exception {

        String url = "https://patentscope.wipo.int/search/pt/detail.jsf?docId="+id+"&redirectedID=true";
        ProcessRecordDto processRecordDto;
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");

        WebDriver driver = new ChromeDriver(options);
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(1500));


        try {
            WebElement title = driver.findElement(By.cssSelector(".PCTtitle"));
            WebElement claimant = driver.findElement(By.xpath("/html/body/div[2]/div[5]/div/div[1]/div[2]/form/div/div/div/div[1]/div/div/div[2]/div/div[1]/div[8]/span[2]/span/ul"));
            WebElement dataPubText = driver.findElement(By.xpath("/html/body/div[2]/div[5]/div/div[1]/div[2]/form/div/div/div/div[1]/div/div/div[2]/div/div[1]/div[2]/span[2]"));
            WebElement internationalOrder = driver.findElement(By.xpath("/html/body/div[2]/div[5]/div/div[1]/div[2]/form/div/div/div/div[1]/div/div/div[2]/div/div[1]/div[3]/span[2]"));

            String textDateStr = dataPubText.getText();
            String[] textDate = textDateStr.split("\\.");

            LocalDate localDate = LocalDate.of(Integer.parseInt(textDate[2]), Integer.parseInt(textDate[1]), Integer.parseInt(textDate[0]));
            Date publicationDate = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

            processRecordDto = new ProcessRecordDto(title.getText(), id, internationalOrder.getText(), publicationDate, claimant.getText());

            driver.quit();

            return ResponseEntity.status(HttpStatus.OK).body(processRecordDto);

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("null");
        }

    }

}
