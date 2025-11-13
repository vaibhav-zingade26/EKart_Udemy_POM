package PageObjects;

import Utilities.abstractCommanMethod;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends abstractCommanMethod {
    WebDriver driver;

    public LandingPage( WebDriver driver){
        super(driver);
        this.driver= driver;
        PageFactory.initElements(driver,this);
    }

    //WebElement email=driver.findElement(By.id("userEmail"));

    @FindBy(id="userEmail")
    WebElement email;

    @FindBy(id="userPassword")
    WebElement pwd;

    @FindBy(id="login")
    WebElement login;

    public dashboardPage loginApp(String emailId, String Password) throws InterruptedException {
        email.sendKeys(emailId);
        pwd.sendKeys(Password);
        login.click();
        zoomOutWebPage();
        dashboardPage dashboardPage = new dashboardPage(driver);
        return dashboardPage;

    }
    public void goTo(){
        driver.get("https://rahulshettyacademy.com/client");
    }

}
