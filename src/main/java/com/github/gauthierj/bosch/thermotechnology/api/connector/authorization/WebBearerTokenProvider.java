package com.github.gauthierj.bosch.thermotechnology.api.connector.authorization;

import com.github.gauthierj.bosch.thermotechnology.api.connector.AuthorizationProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.util.concurrent.TimeUnit;

public class WebBearerTokenProvider implements AuthorizationProvider {

    private final String boschId;
    private final String password;

    public WebBearerTokenProvider(String boschId, String password) {
        this.boschId = boschId;
        this.password = password;
    }

    @Override
    public String getAuthorization() {
            System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
            ChromeOptions options = new ChromeOptions();
            options.setHeadless(true);
            WebDriver driver = new ChromeDriver(options);
        try {
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.get("https://developer.bosch.com/");
            driver.manage().window().setSize(new Dimension(1200, 731));
            driver.findElement(By.className("BoschPrivacySettingsV2__button")).click();
            driver.findElement(By.id("signInRegister")).click();
            driver.findElement(By.linkText("Bosch ID")).click();
            driver.findElement(By.id("email-field")).click();
            driver.findElement(By.id("email-field")).sendKeys(boschId);
            driver.findElement(By.id("password-field")).click();
            driver.findElement(By.id("password-field")).sendKeys(password);
            driver.findElement(By.cssSelector(".col-sm-4 > .A-Button")).click();
            driver.get("https://developer.bosch.com/products-and-services/apis/bosch-thermotechnology-device-api/tryout");
            driver.findElement(By.id("btnStartTesting")).click();
            driver.findElement(By.cssSelector("div:nth-child(1) > .swagger-input img")).click();
            return (String) Toolkit.getDefaultToolkit()
                    .getSystemClipboard().getData(DataFlavor.stringFlavor);
        } catch (Exception e) {
            throw new IllegalStateException("Cannot get Bearer token", e);
        } finally {
            driver.quit();
        }
    }
}
