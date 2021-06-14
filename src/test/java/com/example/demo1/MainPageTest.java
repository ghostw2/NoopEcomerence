package com.example.demo1;


import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class MainPageTest {
    public ExtentReports extent;
    public ExtentTest extentTest;
    public  WebDriver driver;
    private MainPage mainPage;
    private LogInPage logInPage;
    private RegisterFormPage registerFormPage;
    private LogInSubmit logInSubmit;
    private DashboardPage dashboardPage;
    @BeforeTest
    public void setExtent(){
        extent = new ExtentReports(System.getProperty("user.dir")+"/test-output/ExtentReport.html", true);
        extent.addSystemInfo("Host Name", "Menri Windows");
        extent.addSystemInfo("User Name", "ghostw2");
        extent.addSystemInfo("Environment", "Planet Namek");

    }

    @AfterTest
    public void endReport(){
        extent.flush();
        extent.close();
    }
    public static String getScreenshot(WebDriver driver, String screenshotName) throws IOException {
        String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        // after execution, you could see a folder "FailedTestsScreenshots"
        // under src folder
        String destination = System.getProperty("user.dir") + "/FailedTestsScreenshots/" + screenshotName + dateName
                + ".png";
        File finalDestination = new File(destination);
        FileUtils.copyFile(source, finalDestination);
        return destination;
    }


    @BeforeMethod
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

    @AfterMethod
    public void tearDown(ITestResult result) throws IOException {
        if(result.getStatus()==ITestResult.FAILURE){
            extentTest.log(LogStatus.FAIL, "TEST CASE FAILED IS "+result.getName()); //to add name in extent report
            extentTest.log(LogStatus.FAIL, "TEST CASE FAILED IS "+result.getThrowable()); //to add error/exception in extent report

            String screenshotPath = getScreenshot(driver, result.getName());
            extentTest.log(LogStatus.FAIL, extentTest.addScreenCapture(screenshotPath)); //to add screenshot in extent report
            //extentTest.log(LogStatus.FAIL, extentTest.addScreencast(screenshotPath)); //to add screencast/video in extent report
        }
        else if(result.getStatus()==ITestResult.SKIP){
            extentTest.log(LogStatus.SKIP, "Test Case SKIPPED IS " + result.getName());
        }
        else if(result.getStatus()==ITestResult.SUCCESS){
            extentTest.log(LogStatus.PASS, "Test Case PASSED IS " + result.getName());

        }


        extent.endTest(extentTest); //ending test and ends the current test and prepare to create html report
        driver.quit();
    }


    @Test
    public void Test() throws InterruptedException {
        extentTest = extent.startTest("Test");
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
