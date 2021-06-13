package com.example.demo1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class LogInSubmit {
    WebDriver driver;
    @FindBy(xpath =PathRepo.logInA)
    WebElement logIn;
    @FindBy(xpath = PathRepo.inEmail)
    WebElement inEmail;
    @FindBy(xpath = PathRepo.inPassword)
    WebElement inPassword;
    @FindBy(xpath = PathRepo.logInBtn)
    WebElement logInBtn;
    @FindBy(xpath = PathRepo.logOutA)
    WebElement logOut;
    @FindBy(xpath = PathRepo.myAccountA)
    WebElement myAcount;
    @FindBy(xpath = "//input[@value=('M')]")
    WebElement inputMale;
    @FindBy(xpath = "//input[@value=('F')]")
    WebElement inputFeMale;
    @FindBy(xpath =PathRepo.firstNameInput)
    WebElement firstName;
    @FindBy(xpath =PathRepo.lastNameInput )
    WebElement lastName;
    @FindBy(xpath =PathRepo.inEmail )
    WebElement email;
    @FindBy(xpath =PathRepo.inCompany )
    WebElement company;
    @FindBy(xpath = PathRepo.newsletter)
    WebElement newsletter;
    public LogInSubmit(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver=driver;

    }
    public void logInAndOut(){
        logIn.click();
        inEmail.sendKeys(PathRepo.mail);
        inPassword.sendKeys(PathRepo.password);
        logInBtn.click();
        assertEquals(driver.findElement(By.xpath("//h2[text()=\"Welcome to our store\"]")).getText(),
                "Welcome to our store");
        logOut.click();
    }
    public void  logIn(){
        logIn.click();
        inEmail.sendKeys(PathRepo.mail);
        inPassword.sendKeys(PathRepo.password);
        logInBtn.click();
        myAcount.click();

    }
    public void verifyAcount(){
        assertEquals((inputMale.isSelected()?inputMale.getAttribute("value"):inputFeMale.getAttribute("value")),
               String.valueOf( PathRepo.gender));
        assertEquals(firstName.getAttribute("value"),PathRepo.fname);
        assertEquals(lastName.getAttribute("value"),PathRepo.lname);


        Select dateDay=new Select(driver.findElement(By.xpath(PathRepo.bDay)));
        assertEquals(dateDay.getFirstSelectedOption().getText(),PathRepo.Day);
        Select dateMonth=new Select(driver.findElement(By.xpath(PathRepo.bMonth)));
        assertEquals(dateMonth.getFirstSelectedOption().getText(),PathRepo.Month);
        Select dateYear=new Select(driver.findElement(By.xpath(PathRepo.bYear)));
        assertEquals(dateYear.getFirstSelectedOption().getText(),PathRepo.Year);
        assertEquals(email.getAttribute("value"),PathRepo.mail);
        assertEquals(company.getAttribute("value"),PathRepo.company);
        assertTrue(newsletter.isSelected());
        logOut.click();
    }




}


