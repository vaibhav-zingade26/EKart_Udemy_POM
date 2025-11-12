package PageObjects;

import Utilities.base;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage extends base {
    WebDriver driver;

    public CartPage(WebDriver driver) {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy (xpath="//button[normalize-space()='Checkout']")
    WebElement CheckOut;

    public void checkOut(){

    }

}
