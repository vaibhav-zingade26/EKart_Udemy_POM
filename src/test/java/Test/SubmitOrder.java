package Test;

import BasePkg.BaseTest;
import PageObjects.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class SubmitOrder extends BaseTest {
    String productName="ADIDAS ORIGINAL";
    String productName1="ZARA COAT 3";
    String countryName="India";

    @Test(groups = "Purchase")
    public void TC01_Basic_Add_Product() throws InterruptedException, IOException {
        //submt the order
        //LandingPage landingPage=launchApplication();
        DashboardPage dashboardPage=landingPage.loginApp("vaibhav26@gmail.com","VacZ@1234");
        dashboardPage.addProductToCart(productName);
        CartPage cartPage=dashboardPage.goToCart();
        PlaceOrder placeOrder=cartPage.checkOut();
        String confirmMsg=placeOrder.verifySucessMsg(countryName);
        Assert.assertEquals(confirmMsg,"THANKYOU FOR THE ORDER.");
    }

    @Test(dependsOnMethods = {"TC01"})
    public void TC02_Validate_Product() throws IOException, InterruptedException {
        //In orders page verify submitted order
        DashboardPage dashboardPage=landingPage.loginApp("vaibhav26@gmail.com","VacZ@1234");
        OrderPage orderPage =dashboardPage.goToOrders();
        Assert.assertTrue(orderPage.validateProductOrdered(productName));
    }

    @Test (dataProvider="getData")
    public void TC_03_UsingDataProvider(String email,String pwd,String product) throws IOException, InterruptedException {
        DashboardPage dashboardPage=landingPage.loginApp(email,pwd);
        dashboardPage.addProductToCart(product);
        CartPage cartPage=dashboardPage.goToCart();
        PlaceOrder placeOrder=cartPage.checkOut();
        String confirmMsg=placeOrder.verifySucessMsg(countryName);
        Assert.assertEquals(confirmMsg,"THANKYOU FOR THE ORDER.");
    }

    @Test (dataProvider="getDataUsingMap" , groups = "Purchase")
    public void TC_04_UsingDataProvider_HasMap(HashMap<String,String>input) throws IOException, InterruptedException {
        DashboardPage dashboardPage=landingPage.loginApp(input.get("email"),input.get("pwd"));
        dashboardPage.addProductToCart(input.get("product"));
        CartPage cartPage=dashboardPage.goToCart();
        PlaceOrder placeOrder=cartPage.checkOut();
        String confirmMsg=placeOrder.verifySucessMsg(countryName);
        Assert.assertEquals(confirmMsg,"THANKYOU FOR THE ORDER.");
    }

    @Test
    public void TC_Verify_Card_Is_Emptied() throws InterruptedException {
        DashboardPage dashboardPage=landingPage.loginApp("vaibhav26@gmail.com","VacZ@1234");
        dashboardPage.addProductToCart(productName);
        dashboardPage.addProductToCart(productName1);
        CartPage cartPage=dashboardPage.goToCart();
        Assert.assertTrue(cartPage.verifyAddedProd(productName,productName1));
        cartPage.deleteAddedProd();
        Assert.assertEquals(cartPage.deleteAddedProd(),"No Products in Your Cart !");
    }







}
