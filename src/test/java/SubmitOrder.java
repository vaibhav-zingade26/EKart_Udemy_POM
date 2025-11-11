import PageObjects.LandingPage;
import PageObjects.dashboardPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class SubmitOrder {
    @Test
    public void TC01(){
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        LandingPage landingPage = new LandingPage(driver);
        landingPage.goTo();
        landingPage.loginApp("vaibhav26@gmail.com","VacZ@1234");
        dashboardPage dashboardPage = new dashboardPage(driver);
        List<WebElement> products =dashboardPage.getListOfProducts();
        dashboardPage.addProductToCart("ZARA COAT 3");
    }
}
