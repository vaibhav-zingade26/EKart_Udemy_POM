package PageObjects;

import Utilities.Base;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PlaceOrder extends Base {
    WebDriver driver;

    public PlaceOrder(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[@class='btnn action__submit ng-star-inserted']")
    private WebElement PlaceAnOrder;

    @FindBy(xpath = "//input[@placeholder='Select Country']")
    private WebElement enterCountry;
    @FindBy(css = ".ta-results")
    private WebElement listOfCountries;
    @FindBy(css = ".ta-results button:nth-of-type(2)")
    private WebElement Country;
    @FindBy(css = ".hero-primary")
    private WebElement msg;

    public String verifySucessMsg(String CountryName) {
        enterCountry.sendKeys(CountryName);
        explicitWait(listOfCountries);
        Country.click();
        PlaceAnOrder.click();
        return msg.getText();
    }


}
