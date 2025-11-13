package Utilities;

import PageObjects.CartPage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class abstractCommanMethod {
    WebDriver driver;
    public abstractCommanMethod(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css="[routerlink*='cart']")
    WebElement cart;

    public void zoomOutWebPage() throws InterruptedException {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        Thread.sleep(100);
        // Zoom out the page to 50%
        //System.out.println("Zooming out to 50%");
        jse.executeScript("document.body.style.zoom='70%'");
        Thread.sleep(100);
    }

    public void explicitWait(By locator){
        WebDriverWait w= new WebDriverWait(driver, Duration.ofSeconds(10));
        w.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void explicitWait(WebElement ele){
        WebDriverWait w= new WebDriverWait(driver, Duration.ofSeconds(10));
        w.until(ExpectedConditions.visibilityOf(ele));
    }

    public CartPage goToCart() throws InterruptedException {
        Thread.sleep(1000);
        //explicitWait(cart);
        cart.click();
        CartPage cartPage = new CartPage(driver);
        return cartPage;
    }
}
