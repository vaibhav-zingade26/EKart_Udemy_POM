package StepDefinations;

import BasePkg.BaseTest;
import PageObjects.*;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;

import java.io.IOException;

@Epic("E-Commerce Application")
public class StepDefinationsImpl extends BaseTest {
    DashboardPage dashboardPage;
    CartPage cartPage;
    PlaceOrder placeOrder;
    OrderPage orderPage;
    String countryName="India";
    String orderPageUrl="https://rahulshettyacademy.com/client/#/dashboard/myorders";


    @Given("Land on Ecommerce webside")
    public void land_on_Ecommerce_webside() throws IOException {
        launchApplication();
    }

    @Given("^Logged in with username (.+) and password (.+)$")
    public void logged_in_with_username_and_password(String username,String password) throws InterruptedException {
        dashboardPage=landingPage.loginApp(username,password);
    }

    @Given("^user added the product (.+)$")
    public void user_added_the_product(String product) throws InterruptedException {
        dashboardPage.addProductToCart(product);
    }

    @And("checkout the order")
    public void checkout_the_order() throws InterruptedException {
        cartPage=dashboardPage.goToCart();
        placeOrder=cartPage.checkOut();
    }

 /*   @Then("message should be displayed on confirmationPage.")
    public void message_should_be_displayed_on_confirmation_page(String string) {
        System.out.println("I ma in method");
        String actualMsg = placeOrder.verifySucessMsg(countryName);
        Assert.assertEquals(actualMsg, string);
    }*/

    @Then("^message (.+) should displayed on login Page$")
    public void message_should_be_displayed_on_login_page(String expectedMsg) {
        String actualMsg = landingPage.errorMSg();  // method to get the UI message
        Assert.assertEquals(actualMsg, expectedMsg);
    }


    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            byte[] screenshot =
                    ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

            scenario.attach(screenshot, "image/png", "Failure Screenshot");
        }

        if (driver != null) {
            driver.quit();
        }
    }

    @Then("{string} message should be displayed on confirmationPage.")
    public void messageShouldBeDisplayedOnConfirmationPage(String string) {
        System.out.println("I ma in method");
        String actualMsg = placeOrder.verifySucessMsg(countryName);
        Assert.assertEquals(actualMsg, string);
    }

    @When("User clicked on orders")
    public void user_clicked_on_orders() {
        //click on order
        System.out.println("I ma in method");
        orderPage.goToOrders();


    }

    @Then("User should land on orders page")
    public void user_should_land_on_orders_page() {
        //verify order page by title
        String orderUrl=driver.getCurrentUrl();
        Assert.assertEquals(orderUrl,orderPageUrl);
    }
}
