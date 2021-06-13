package com.example.demo1;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import static org.testng.AssertJUnit.assertEquals;

public class RegisterFormPage {

    WebDriver driver;
    @FindBy(xpath =PathRepo.maleLabel)
    WebElement genderM;
    @FindBy(xpath =PathRepo.femaleLabel )
    WebElement getGenderF;
    @FindBy(xpath =PathRepo.firstNameInput)

    WebElement firstName;
    @FindBy(xpath =PathRepo.lastNameInput )
    WebElement lastName;
    @FindBy(xpath =PathRepo.inEmail )
    WebElement email;
    @FindBy(xpath =PathRepo.inPassword )
    WebElement password;
    @FindBy(xpath =PathRepo.inRePassword )
    WebElement rePassword;

//    @FindBy(xpath =PathRepo.bDay)
//    Select dateDay;
//    @FindBy(xpath =PathRepo.bMonth )
//    Select dateMonth;
//    @FindBy(xpath =PathRepo.bYear )
//    Select dateYear;
    @FindBy(xpath =PathRepo.inCompany )
    WebElement company;
    @FindBy(xpath =PathRepo.reButton )
    WebElement registerButtonInForm;
    @FindBy(xpath = PathRepo.logOutA)
    WebElement logOut;
    public RegisterFormPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver=driver;
//        this.driver=driver;
    }
    public void submitRegisterForm( char gender){
        if (gender == 'M') {
            genderM.click();
        } else {
            getGenderF.click();
        }
        firstName.sendKeys(PathRepo.fname);
        lastName.sendKeys(PathRepo.lname);
        Select dateDay=new Select(driver.findElement(By.xpath(PathRepo.bDay)));
        dateDay.selectByVisibleText(PathRepo.Day);
        Select dateMonth=new Select(driver.findElement(By.xpath(PathRepo.bMonth)));
        dateMonth.selectByVisibleText(PathRepo.Month);
        Select dateYear=new Select(driver.findElement(By.xpath(PathRepo.bYear)));
        dateYear.selectByVisibleText(PathRepo.Year);


        email.sendKeys(PathRepo.mail);

        company.sendKeys(PathRepo.company);

        password.sendKeys(PathRepo.password);


        rePassword.sendKeys(PathRepo.password);

        registerButtonInForm.click();
        assertEquals(driver.findElement(By.cssSelector("div.result")).getText(),"Your registration completed");
        logOut.click();
    }
}
