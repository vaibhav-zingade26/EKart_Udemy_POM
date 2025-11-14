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

import java.io.IOException;
import java.time.Duration;

public class submitOrder extends Base {
    String productName="ADIDAS ORIGINAL";
    String countryName="India";

    @Test
    public void TC01() throws InterruptedException, IOException {
        LandingPage landingPage=launchApplication();
        dashboardPage dashboardPage=landingPage.loginApp("vaibhav26@gmail.com","VacZ@1234");
        dashboardPage.addProductToCart(productName);
        CartPage cartPage=dashboardPage.goToCart();
        PlaceOrder placeOrder=cartPage.checkOut();
        String confirmMsg=placeOrder.verifySucessMsg(countryName);
        Assert.assertEquals(confirmMsg,"THANKYOU FOR THE ORDER.");
    }
}
