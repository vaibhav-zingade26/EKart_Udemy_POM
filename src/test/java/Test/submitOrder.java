package Test;

import BasePkg.Base;
import PageObjects.CartPage;
import PageObjects.LandingPage;
import PageObjects.PlaceOrder;
import PageObjects.dashboardPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class submitOrder extends Base {
    String productName="ADIDAS ORIGINAL";
    String countryName="India";

    public submitOrder(WebDriver driver) {
        super(driver);
    }

    @Test
    public void TC01() throws InterruptedException {
       /* WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        LandingPage landingPage = new LandingPage(driver);
        landingPage.goTo();*/
        launchApplication();
        dashboardPage dashboardPage=landingPage.loginApp("vaibhav26@gmail.com","VacZ@1234");
        dashboardPage.addProductToCart(productName);
        CartPage cartPage=dashboardPage.goToCart();
        PlaceOrder placeOrder=cartPage.checkOut();
        String confirmMsg=placeOrder.verifySucessMsg(countryName);
        Assert.assertEquals(confirmMsg,"THANKYOU FOR THE ORDER.");
    }
}
