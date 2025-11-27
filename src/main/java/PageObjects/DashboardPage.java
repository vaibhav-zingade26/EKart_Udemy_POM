package PageObjects;

import Utilities.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class DashboardPage extends Base {

    WebDriver driver;

    public DashboardPage(WebDriver driver){
        super(driver);
        this.driver= driver;
        PageFactory.initElements(driver,this);
    }

    //List<WebElement> cardBody=driver.findElements(By.cssSelector(".card-body"));
    @FindBy(css=".card-body")
    List<WebElement> products;

    By productBy=By.cssSelector(".card-body");
    By addToCart=By.cssSelector(".card-body button:last-of-type");

    public List<WebElement> getListOfProducts() {
        explicitWait(productBy);
        return products;
    }

    public WebElement getProductByName(String productName){
        WebElement desiredProd=getListOfProducts().stream().filter(zara->
                zara.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
        return desiredProd;

    }

    public void addProductToCart(String productName){
        WebElement desiredProd=getProductByName(productName);
        desiredProd.findElement(addToCart).click();
    }

}




