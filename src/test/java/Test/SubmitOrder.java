package Test;

import BasePkg.BaseTest;
import PageObjects.CartPage;
import PageObjects.DashboardPage;
import PageObjects.OrderPage;
import PageObjects.PlaceOrder;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.*;

public class SubmitOrder extends BaseTest {
    DashboardPage dashboardPage;

    @Test(groups = "Purchase")
    public void TC01_Basic_Add_Product() throws InterruptedException, IOException {
        //submt the order
        //LandingPage landingPage=launchApplication();
        DashboardPage dashboardPage = landingPage.loginApp("vaibhav26@gmail.com", "VacZ@1234");
        dashboardPage.addProductToCart(prop.getProperty("productName"));
        CartPage cartPage = dashboardPage.goToCart();
        PlaceOrder placeOrder = cartPage.checkOut();
        String confirmMsg = placeOrder.verifySucessMsg(prop.getProperty("countryName"));
        Assert.assertEquals(confirmMsg, "THANKYOU FOR THE ORDER.");
    }

    @Test(dependsOnMethods = {"TC01"})
    public void TC02_Validate_Product() throws IOException, InterruptedException {
        //In orders page verify submitted order
        DashboardPage dashboardPage = landingPage.loginApp("vaibhav26@gmail.com", "VacZ@1234");
        OrderPage orderPage = dashboardPage.goToOrders();
        Assert.assertTrue(orderPage.validateProductOrdered(prop.getProperty("productName")));
    }

    @Test(dataProvider = "getData")
    public void TC_03_UsingDataProvider(String email, String pwd, String product) throws IOException, InterruptedException {
        DashboardPage dashboardPage = landingPage.loginApp(email, pwd);
        dashboardPage.addProductToCart(product);
        CartPage cartPage = dashboardPage.goToCart();
        PlaceOrder placeOrder = cartPage.checkOut();
        String confirmMsg = placeOrder.verifySucessMsg(prop.getProperty("countryName"));
        Assert.assertEquals(confirmMsg, "THANKYOU FOR THE ORDER.");
    }

    @Test(dataProvider = "getDataUsingMap", groups = "Purchase")
    public void TC_04_UsingDataProvider_HasMap(HashMap<String, String> input) throws IOException, InterruptedException {
        DashboardPage dashboardPage = landingPage.loginApp(input.get("email"), input.get("pwd"));
        dashboardPage.addProductToCart(input.get("product"));
        CartPage cartPage = dashboardPage.goToCart();
        PlaceOrder placeOrder = cartPage.checkOut();
        String confirmMsg = placeOrder.verifySucessMsg(prop.getProperty("countryName"));
        Assert.assertEquals(confirmMsg, "THANKYOU FOR THE ORDER.");
    }

    @Test
    public void TC_Verify_Card_Is_Emptied() throws InterruptedException {
        DashboardPage dashboardPage = landingPage.loginApp("vaibhav26@gmail.com", "VacZ@1234");
        dashboardPage.addProductToCart(prop.getProperty("productName"));
        dashboardPage.addProductToCart(prop.getProperty("productName1"));
        CartPage cartPage = dashboardPage.goToCart();
        Assert.assertTrue(cartPage.verifyAddedProd(prop.getProperty("productName"), prop.getProperty("productName1")));
        cartPage.deleteAddedProd();
        Assert.assertEquals(cartPage.deleteAddedProd(), "No Products in Your Cart !");
    }

    @Test
    public void TC_User_should_able_to_navigate_order_cartPage() throws InterruptedException {
        landingPage.loginApp("vaibhav26@gmail.com", "VacZ@1234");
        OrderPage orderPage = new OrderPage(driver);
        orderPage.goToOrders();
        Assert.assertEquals(getCurrentURL(), prop.getProperty("orderPageUrl"));
        driver.navigate().back();
        CartPage cart = new CartPage(driver);
        cart.goToCart();
        Assert.assertEquals(getCurrentURL(), prop.getProperty("cartPageUrl"));
    }

    @Test
    public void putAllProdAndValueIntoMap() throws InterruptedException {
        dashboardPage = landingPage.loginApp("vaibhav26@gmail.com", "VacZ@1234");
        List<WebElement> pro = dashboardPage.getListOfProductsName();
        Map<String, String> map = new HashMap<>();
        Set<String> Names = new HashSet<>();
        Set<String> price = new HashSet<>();
        for (WebElement p : pro) {
            Names.add(p.getText());
        }
        Names.remove("IPHONE 13 PRO");
        System.out.println(Names);
        dashboardPage.addProductToCart("AUTOMATION 8");
        String p1 = dashboardPage.getValuesofProduct("AUTOMATION 8");
        System.out.println(p1);
    }


}
