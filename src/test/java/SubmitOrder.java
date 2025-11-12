import PageObjects.LandingPage;
import PageObjects.dashboardPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class SubmitOrder {
    String productName="ADIDAS ORIGINAL";
    @Test
    public void TC01() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        LandingPage landingPage = new LandingPage(driver);
        landingPage.goTo();
        dashboardPage dashboardPage=landingPage.loginApp("vaibhav26@gmail.com","VacZ@1234");
        dashboardPage.addProductToCart(productName);
        dashboardPage.goToCart();
        driver.findElement(By.xpath("//button[normalize-space()='Checkout']")).click();
        driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys("India");
        //w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
        dashboardPage.explicitWait(By.cssSelector(".ta-results"));
        driver.findElement(By.cssSelector(".ta-results button:nth-of-type(2)")).click();
        driver.findElement(By.xpath("//a[@class='btnn action__submit ng-star-inserted']")).click();
        String confirmMsg=driver.findElement(By.cssSelector(".hero-primary")).getText();
        Assert.assertEquals(confirmMsg,"THANKYOU FOR THE ORDER.");
    }
}
