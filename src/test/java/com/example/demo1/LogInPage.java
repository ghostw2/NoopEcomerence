package com.example.demo1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class LogInPage  {
    WebDriver driver;
    @FindBy(xpath = PathRepo.registerButton)
    WebElement registerButton;
    public LogInPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver=driver;
    }
    public void pressRegister() throws InterruptedException {

        WebDriverWait wait=new WebDriverWait(driver, 10);
        WebElement webElement =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()=\"Subscribe\"]")));


        registerButton.click();
    }


}
