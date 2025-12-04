package PageObjects;

import Utilities.Base;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class CartPage extends Base {
    WebDriver driver;

    public CartPage(WebDriver driver) {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy (xpath="//button[normalize-space()='Checkout']")
    WebElement checkOut;
    @FindBy(xpath = "//div[@class='cartSection']//h3")
    List<WebElement> p;
    @FindBy(xpath = "//button[@class='btn btn-danger']")
    List<WebElement> deleteProd;
    @FindBy(xpath = "//div//h1[contains( text( ),'No Products')]")
    WebElement EmptyMsg;

    public boolean verifyAddedProd(String one, String two){
        List<WebElement> pr=p;
        ArrayList<String > b= new ArrayList<>();
        for(WebElement a:pr){
            System.out.println(a.getText());
            b.add(a.getText());
        }
        if(b.contains(one) && b.contains(two)){
            return true;
        }
        return false;
    }

    public String deleteAddedProd(){
        clickAllElements(deleteProd);
        return EmptyMsg.getText();
    }

    public PlaceOrder checkOut(){
    checkOut.click();
    PlaceOrder submitOrder = new PlaceOrder(driver);
    return submitOrder;
    }

}
