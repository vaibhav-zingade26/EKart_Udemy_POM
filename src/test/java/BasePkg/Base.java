package BasePkg;

import PageObjects.LandingPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class Base {
    WebDriver driver;

    public Base(WebDriver driver){
        this.driver=driver;
    }

    public LandingPage launchApplication(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        LandingPage landingPage = new LandingPage(driver);
        landingPage.goTo();
        return landingPage;
    }
}
