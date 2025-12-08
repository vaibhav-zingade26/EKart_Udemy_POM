package PageObjects;

import Utilities.Base;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends Base {
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

    @FindBy(xpath = "//button[contains(text(),' Sign Out ')]")
    WebElement logOut;

    //div[aria-label='Incorrect email or password.']
    //document.querySelector(".ng-tns-c4-8.ng-star-inserted.ng-trigger.ng-trigger-flyInOut.ngx-toastr.toast-error")

    @FindBy(xpath = "//div[@aria-label='Incorrect email or password.']")
    WebElement errorMessage;

    @FindBy(xpath="//div[contains(text(), 'Email')]")
    WebElement errForEmail;

    public DashboardPage loginApp(String emailId, String Password) throws InterruptedException {
        email.sendKeys(emailId);
        pwd.sendKeys(Password);
        login.click();
        zoomOutWebPage();
        DashboardPage dashboardPage = new DashboardPage(driver);
        return dashboardPage;

    }

    public void logOutApp(){
        logOut.click();
    }
    public void goTo(){
        driver.get("https://rahulshettyacademy.com/client");
    }

    public String errorMSg(){
        explicitWait(errorMessage);
        return errorMessage.getText();
    }

    public String emailWrongValidations() throws InterruptedException {
        //explicitWait(errForEmail);
        Thread.sleep(1000);
        return errForEmail.getText();
    }

}
