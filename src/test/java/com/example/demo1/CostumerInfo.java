package com.example.demo1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class CostumerInfo {
    public WebDriver driver;
    public WebElement RegisterA;
    public WebElement genderRadio;
    public WebElement firstNameInput;
    public WebElement lastNameInput;
    public WebElement dateDay;
    public WebElement dateMonth;
    public WebElement dateYear;
    public WebElement Email;
    public WebElement CompanyName;
    public WebElement newsletter;
    public CostumerInfo(WebDriver driver){
        this.driver=driver;
    }

    public void checkGender(){
       assertTrue( driver.findElement(By.xpath("//div//input[@value=\"M\"]")).isSelected());
    }
    public void checkName(){
//        firstNameInput=driver.findElement(By.xpath());
//        assertEquals(firstNameInput,)
    }
    public void lastName(){

    }
    public void checkDate(){

    }
    public void checkEmail(){

    }
    public void checkCompanyName(){

    }
    public void checkNewsletter(){

    }

}
