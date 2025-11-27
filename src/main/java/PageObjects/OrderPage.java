package PageObjects;

import Utilities.Base;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class OrderPage extends Base {
    WebDriver driver;

    public OrderPage(WebDriver driver) {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css="tr td:nth-child(3)")
    List<WebElement> orderedProducts;

    public boolean validateProductOrdered(String productName) {
        for(WebElement p:orderedProducts){
            if(p.getText().equals(productName)){
                return true;
            }
        }
        return false;
    }
}
