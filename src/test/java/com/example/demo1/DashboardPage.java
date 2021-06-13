package com.example.demo1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;


import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class DashboardPage {
    WebDriver driver;
    @FindBy(xpath = PathRepo.computersA)
    WebElement computersA;
    @FindBy(xpath = PathRepo.notebooksA)
    WebElement notebooks;

    public DashboardPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver=driver;

    }

public void goTonotebooks(){

    Actions actions= new Actions(driver);
   actions.moveToElement(computersA).build().perform();
   notebooks.click();
    //WebDriverWait wait= new WebDriverWait(driver,10);
    assertEquals(driver.getTitle(),"nopCommerce demo store. Notebooks");

}
public void changePreferences()  {
    Select select=new Select(driver.findElement(By.xpath(PathRepo.pagesizeSelect)));
    select.selectByVisibleText("9");
    numItemsAssert(6);
    WebElement label16= driver.findElement(By.xpath(PathRepo.Gb16));
     label16.click();
    WebDriverWait wait= new WebDriverWait(driver,10);
    Boolean element=wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//img[@title=\"Show details for Apple MacBook Pro 13-inch\"]"))));
     numItemsAssert(1);

     label16.click();
    WebElement e = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//img[@title=\"Show details for Apple MacBook Pro 13-inch\"]"))));
     numItemsAssert(6);
}
    public void addItemsToWishList() {
        List<WebElement> elements=driver.findElements(By.xpath(PathRepo.toWishlistButtons));
        addToWishlist(1,elements);
        verifyItemToWishList();
        addToWishlist(2,elements);
        verifyItemToWishList();

    }


    public void addItemsToShoppingCard(){
        List<WebElement> elements=driver.findElements(By.xpath(PathRepo.toShoppingCardButtons));
        addItemToShoppingCard(3,elements);
        verifyItemToCard();
        addItemToShoppingCard(4,elements);
        verifyItemToCard();
        addItemToShoppingCard(5,elements);
        verifyItemToCard();
    }
    public void addItemToShoppingCard(int index,List<WebElement> list){
        list.get(index).click();

    }
    public void verifyItemToCard() {
        assertEquals("The product has been added to your shopping cart", driver.findElement(By.xpath("" +
                "//div[@class=\"bar-notification success\" and  @style=\"display: block;\"]/p")).getText());
        driver.findElement(By.xpath("//div[@class=\"bar-notification success\" and  @style=\"display: block;\"]/span")).click();
    }
    public void numItemsAssert(int expexted){
        List<WebElement> items =driver.findElements(By.className("item-box"));
        assertEquals(items.size(),expexted);

    }
    public void addToWishlist(int index,List<WebElement> list){
        list.get(index).click();
}
public void verifyItemToWishList(){
        assertEquals("The product has been added to your wishlist",driver.findElement(By.xpath("//div[@class=\"bar-notification success\" and  @style=\"display: block;\"]/p")).getText());
        driver.findElement(By.xpath("//div[@class=\"bar-notification success\" and  @style=\"display: block;\"]/span")).click();
    }
    public void verifyCardWishlist(){
        assertEquals(driver.findElement(By.xpath("//li[@id=\"topcartlink\"]//span[@class=\"cart-qty\"]")).getText(),"(3)");
        assertEquals(driver.findElement(By.xpath("//li//span[@class=\"wishlist-qty\"]")).getText(),"(2)");


    }
    public void shoppingCardMenu(){
        Actions actions=new Actions(driver);
        actions.moveToElement(driver.findElement(By.xpath("//span[@class=\"cart-label\"]"))).build().perform();
        WebElement goToCard= driver.findElement(By.xpath("//button[text()=\"Go to cart\"]"));
        WebElement wait=new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button[text()=\"Go to cart\"]"))));
        assertTrue(goToCard.isDisplayed());
        goToCard.click();
        WebDriverWait wait2= new WebDriverWait(driver,10);
        WebElement h1=wait2.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//h1[text()=\"Shopping cart\"]"))));
        WebElement hi=driver.findElement(By.xpath("//h1[text()=\"Shopping cart\"]"));
        assertEquals(driver.findElement(By.xpath("//h1[text()=\"Shopping cart\"]")).getText(),"Shopping cart");
        List<WebElement> b2uttons=driver.findElements(By.xpath("//div[@class=\"common-buttons\"]//button[@type=\"submit\"]"));
        WebElement estimate=driver.findElement(By.xpath("//div[@class=\"common-buttons\"]//a"));
        assertTrue(b2uttons.get(0).isDisplayed());
        assertTrue(b2uttons.get(1).isDisplayed());
        assertTrue(estimate.isDisplayed());
        List<WebElement> subTotals=driver.findElements(By.xpath("//table[@class=\"cart\"]//td//span[@class=\"product-subtotal\"]"));
         Float total= Float.parseFloat(removeFirst(removeComma(driver.findElement(By.xpath("//span[@class=\"value-summary\"]//strong")).getText())));
         assertEquals(total+"",sum(subTotals)+"");

    }
    public void removinItems(){
        ArrayList<WebElement> removeBtns= (ArrayList<WebElement>) driver.findElements(By.xpath(PathRepo.removeBtns));
        int cont=removeBtns.size();
        removeBtns.get(0).click();
        assertEquals(getNrElements(),--cont);
        removeBtns= (ArrayList<WebElement>) driver.findElements(By.xpath(PathRepo.removeBtns));
        removeBtns.get(0).click();
        assertEquals(getNrElements(),--cont);
        removeBtns= (ArrayList<WebElement>) driver.findElements(By.xpath(PathRepo.removeBtns));
        removeBtns.get(0).click();
        assertEquals(getNrElements(),--cont);
        WebElement empty=driver.findElement(By.xpath("//div[@class=\"no-data\"]"));
        WebElement wait=new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@class=\"no-data\"]"))));
        assertEquals(empty.getText(), "Your Shopping Cart is empty!");
    }
    String removeFirst(String str)
    {

        StringBuilder sb = new StringBuilder(str);
        sb.deleteCharAt(0);
        return sb.toString();
    }
    float sum(List<WebElement> list){
       int sum = 0;
        for(WebElement a:list){
            sum+=Float.parseFloat(removeFirst(removeComma(a.getText())));
        }
        return sum;
    }
    String removeComma(String str){
        return str.replaceAll(",","");
    }
    public int getNrElements(){
        List<WebElement> newList = driver.findElements(By.xpath(PathRepo.removeBtns));
        return newList.size();
    }
}
