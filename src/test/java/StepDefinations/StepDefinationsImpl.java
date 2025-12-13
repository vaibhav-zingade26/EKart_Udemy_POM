package StepDefinations;

import BasePkg.BaseTest;
import PageObjects.CartPage;
import PageObjects.DashboardPage;
import PageObjects.LandingPage;
import PageObjects.PlaceOrder;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.testng.Assert;

import java.io.IOException;

public class StepDefinationsImpl extends BaseTest {
    DashboardPage dashboardPage;
    CartPage cartPage;
    PlaceOrder placeOrder;
    String countryName="India";


    @Given("Land on Ecommerce webside")
    public void land_on_Ecommerce_webside() throws IOException {
        launchApplication();
    }

    @Given("^Logged in with username (.+) and password (.+)$")
    public void logged_in_with_username_and_password(String username,String password) throws InterruptedException {
        dashboardPage=landingPage.loginApp(username,password);
    }

    @Given("^user added the product (.+)$")
    public void user_added_the_product(String product){
        dashboardPage.addProductToCart(product);
    }

    @And("checkout the order")
    public void checkout_the_order() throws InterruptedException {
        cartPage=dashboardPage.goToCart();
        placeOrder=cartPage.checkOut();
    }

    @Then("{string} messsage should displayed on confirmationPage")
    public void messsage_should_displayed(String string){
        String confirmMsg=placeOrder.verifySucessMsg(countryName);
        Assert.assertEquals(confirmMsg,string);
    }

    @Then("{string} messsage should displayed on login Page.")
    public void messsage_should_displayed_on_login_page(String string) {
        Assert.assertEquals(landingPage.errorMSg(),string);
    }


    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }




}
