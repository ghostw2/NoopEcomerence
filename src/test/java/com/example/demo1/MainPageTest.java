package com.example.demo1;



import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.*;

import static org.testng.Assert.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class MainPageTest {
    public  WebDriver driver;
    private MainPage mainPage;
    private LogInPage logInPage;
    private RegisterFormPage registerFormPage;
    private LogInSubmit logInSubmit;
    private DashboardPage dashboardPage;


    @BeforeClass
    public <driver> void setUp() {
        System.setProperty("webdriver.chrome.driver","C:\\Users\\Menri\\Downloads\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://demo.nopcommerce.com/");

        mainPage = new MainPage(driver);
        logInPage=new LogInPage(driver);
        registerFormPage=new RegisterFormPage(driver);
        logInSubmit=new LogInSubmit(driver);
        dashboardPage=new DashboardPage(driver);

    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }


    @Test
    public void Test() throws InterruptedException {
        mainPage.logIn();
        logInPage.pressRegister();
        registerFormPage.submitRegisterForm(PathRepo.gender);
        logInSubmit.logInAndOut();
        logInSubmit.logIn();
        logInSubmit.verifyAcount();
        dashboardPage.goTonotebooks();
        dashboardPage.changePreferences();
        dashboardPage.addItemsToWishList();
        dashboardPage.addItemsToShoppingCard();
        dashboardPage.verifyCardWishlist();
        dashboardPage.shoppingCardMenu();
        dashboardPage.removinItems();
        Thread.sleep(5000);

    }



}
