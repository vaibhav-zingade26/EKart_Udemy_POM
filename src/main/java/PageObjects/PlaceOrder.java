package PageObjects;

import Utilities.abstractCommanMethod;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class PlaceOrder extends abstractCommanMethod {
    WebDriver driver;

    public PlaceOrder(WebDriver driver) {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

     @FindBy(xpath="//a[@class='btnn action__submit ng-star-inserted']")
     WebElement PlaceAnOrder;

    @FindBy(xpath="//input[@placeholder='Select Country']")
    WebElement enterCountry;
    @FindBy(css=".ta-results")
    WebElement listOfCountries;
    @FindBy(css=".ta-results button:nth-of-type(2)")
    WebElement Country;
    @FindBy(css=".hero-primary")
    WebElement msg;

    public String verifySucessMsg(String CountryName){
        enterCountry.sendKeys(CountryName);
        explicitWait(listOfCountries);
        Country.click();
        PlaceAnOrder.click();
        return msg.getText();
    }




}
