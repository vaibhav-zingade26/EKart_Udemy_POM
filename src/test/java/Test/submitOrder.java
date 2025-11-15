package Test;

import BasePkg.Base;
import PageObjects.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;

public class submitOrder extends Base {
    String productName="ADIDAS ORIGINAL";
    String countryName="India";

    @Test
    public void TC01() throws InterruptedException, IOException {
        //submt the order
        LandingPage landingPage=launchApplication();
        dashboardPage dashboardPage=landingPage.loginApp("vaibhav26@gmail.com","VacZ@1234");
        dashboardPage.addProductToCart(productName);
        CartPage cartPage=dashboardPage.goToCart();
        PlaceOrder placeOrder=cartPage.checkOut();
        String confirmMsg=placeOrder.verifySucessMsg(countryName);
        Assert.assertEquals(confirmMsg,"THANKYOU FOR THE ORDER.");
    }

    @Test(dependsOnMethods = {"TC01"})
    public void TC02() throws IOException, InterruptedException {
        //In orders page verify submitted order
        LandingPage landingPage=launchApplication();
        dashboardPage dashboardPage=landingPage.loginApp("vaibhav26@gmail.com","VacZ@1234");
        OrderPage orderPage =dashboardPage.goToOrders();
        Assert.assertTrue(orderPage.validateProductOrdered(productName));
    }






}
