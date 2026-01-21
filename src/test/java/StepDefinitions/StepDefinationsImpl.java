package StepDefinitions;

import BasePkg.BaseTest;
import PageObjects.CartPage;
import PageObjects.DashboardPage;
import PageObjects.OrderPage;
import PageObjects.PlaceOrder;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Epic;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;

import java.io.IOException;

@Epic("E-Commerce Application")
public class StepDefinationsImpl extends BaseTest {
    DashboardPage dashboardPage;
    CartPage cartPage;
    PlaceOrder placeOrder;
    String countryName = "India";
    String orderPageUrl = "https://rahulshettyacademy.com/client/#/dashboard/myorders";
    String cartPageUrl = "https://rahulshettyacademy.com/client/#/dashboard/cart";
    String price;
    String totalValue;



    @Given("Land on Ecommerce webside")
    public void land_on_Ecommerce_webside() throws IOException {
        launchApplication();
    }

    @Given("^Logged in with username (.+) and password (.+)$")
    public void logged_in_with_username_and_password(String username, String password) throws InterruptedException {
        dashboardPage = landingPage.loginApp(username, password);
    }

    @Given("^user added the product (.+)$")
    public void user_added_the_product(String product) throws InterruptedException {
        dashboardPage.addProductToCart(product);
    }

    @And("checkout the order")
    public void checkout_the_order() throws InterruptedException {
        cartPage = dashboardPage.goToCart();
        placeOrder = cartPage.checkOut();
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
        String actualMsg = placeOrder.verifySucessMsg(countryName);
        Assert.assertEquals(actualMsg, string);
    }


    @When("User clicked on orders")
    public void user_clicked_on_orders() {
        OrderPage orderPage = new OrderPage(driver);
        orderPage.goToOrders();

    }

    @Then("User should land on orders page")
    public void user_should_land_on_orders_page() {
        Assert.assertEquals(getCurrentURL(), orderPageUrl);

    }

    @And("User came back on and dashboard click on cart")
    public void userCameBackOnAndDashboardClickOnCart() throws InterruptedException {
        driver.navigate().back();
        CartPage cart = new CartPage(driver);
        cart.goToCart();
    }

    @Then("User should land on Cart page")
    public void userShouldLandOnCartPage() {
        Assert.assertEquals(getCurrentURL(), cartPageUrl);
    }

    @And("^fetch the price of (.+)$")
    public void fetchThePriceOfProduct(String product) {
        price = dashboardPage.priceOfProduct(product);
        System.out.println("price of the " + product + ": " + price);

    }

    @When("^click on view of (.+)$")
    public void clickOnViewOfProduct(String product) {
        dashboardPage.clickOnView(product);
    }

    @Then("^Verify (.+) name and price value$")
    public void verifyProductNameAndPriceValue(String pro) {
        dashboardPage.verifyProductAndPrice(pro, price);
    }

    @When("^user added the products (.+) and (.+)$")
    public void userAddedTheProductsProductAndProduct(String product1,String product2) throws InterruptedException {
        dashboardPage.addProductToCart(product1);
        dashboardPage.addProductToCart(product2);
        String p1=dashboardPage.getValuesofProduct(product1);
        String p2=dashboardPage.getValuesofProduct(product2);
        int totalValueInt=Integer.parseInt(p1.substring(2))+Integer.parseInt(p2.substring(2));
        totalValue=String.valueOf(totalValueInt);
        totalValue="$"+totalValue;
        System.out.println("totalValue is :" +totalValue);
    }

    @Then("Verify total amount in cartPage")
    public void verifyTotalAmountInCartPage() throws InterruptedException {
        CartPage cart = new CartPage(driver);
        cart.goToCart();
        System.out.println("TotalValue "+ cart.totalAmount());
        Assert.assertEquals(cart.totalAmount(),totalValue);
    }

    @When("^user search the products (.+)$")
    public void userSearchTheProductsProduct(String product) {
        dashboardPage.searchProduct(product);
    }

    @Then("^(.+) should come on dashboard$")
    public void productShouldComeOnDashboard(String product) {
        String appearedProduct=dashboardPage.productDisplayed();
        System.out.println("The Displayed product is : "+appearedProduct);
        Assert.assertEquals(product,appearedProduct);
    }
}
