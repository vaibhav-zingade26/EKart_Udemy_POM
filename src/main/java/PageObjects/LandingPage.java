package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage {
    WebDriver driver;

    public LandingPage( WebDriver driver){
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

    public void loginApp(String emailId,String Password){
        email.sendKeys(emailId);
        pwd.sendKeys(Password);
        login.click();
    }
    public void goTo(){
        driver.get("");
    }

}
