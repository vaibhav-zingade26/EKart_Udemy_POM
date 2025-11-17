package Test;

import BasePkg.Base;
import PageObjects.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;

public class submitOrder extends Base {
    String productName="ADIDAS ORIGINAL";
    String countryName="India";

    @Test(groups = "Purchase")
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

    @Test (dataProvider="getData")
    public void TC_03_UsingDataProvider(String email,String pwd,String product) throws IOException, InterruptedException {
        LandingPage landingPage=launchApplication();
        dashboardPage dashboardPage=landingPage.loginApp(email,pwd);
        dashboardPage.addProductToCart(product);
        CartPage cartPage=dashboardPage.goToCart();
        PlaceOrder placeOrder=cartPage.checkOut();
        String confirmMsg=placeOrder.verifySucessMsg(countryName);
        Assert.assertEquals(confirmMsg,"THANKYOU FOR THE ORDER.");
    }

    @Test (dataProvider="getDataUsingMap" , groups = "Purchase")
    public void TC_04_UsingDataProvider_HasMap(HashMap<String,String>input) throws IOException, InterruptedException {
        LandingPage landingPage=launchApplication();
        dashboardPage dashboardPage=landingPage.loginApp(input.get("email"),input.get("pwd"));
        dashboardPage.addProductToCart(input.get("product"));
        CartPage cartPage=dashboardPage.goToCart();
        PlaceOrder placeOrder=cartPage.checkOut();
        String confirmMsg=placeOrder.verifySucessMsg(countryName);
        Assert.assertEquals(confirmMsg,"THANKYOU FOR THE ORDER.");
    }

    @DataProvider
    public Object[][] getData(){
       return new Object[][] { {"vaibhav26@gmail.com","VacZ@1234","ADIDAS ORIGINAL"},{"VacZ@9464.com","VacZ@9464","ZARA COAT 3"} };
    }

    @DataProvider
    public Object[][] getDataUsingMap(){
        HashMap<String,String >map1=new HashMap<>();
        map1.put("email","vaibhav26@gmail.com");
        map1.put("pwd","VacZ@1234");
        map1.put("product","ADIDAS ORIGINAL");

        HashMap<String,String >map2=new HashMap<>();
        map2.put("email","VacZ@9464.com");
        map2.put("pwd","VacZ@9464");
        map2.put("product","ZARA COAT 3");

        return new Object[][] { {map1},{map2} };
    }







}
