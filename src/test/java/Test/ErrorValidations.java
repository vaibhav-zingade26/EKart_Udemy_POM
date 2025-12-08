package Test;

import BasePkg.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class ErrorValidations extends BaseTest {

    @Test(priority = 2)
    public void TC01() throws InterruptedException, IOException {
        landingPage.loginApp("vaibhav26@gmail.com", "dwdwgd");
        Assert.assertEquals("Incorrect email or password.", landingPage.errorMSg());
    }

    @Test(priority = 1)
    public void TC02() throws IOException, InterruptedException {
        //id email id is wrong and pwd is correct "*Enter Valid Email" this string validate
        landingPage.loginApp("vaibhav26.com", "VacZ@1234");
        Assert.assertEquals("*Enter Valid Email", landingPage.emailWrongValidations());
        Assert.assertTrue(false);
    }

    @Test
    public void TC_login_logOut_MultipleTimes() throws InterruptedException {

        for (int i = 0; i < 4; i++) {
            landingPage.loginApp("vaibhav26@gmail.com", "VacZ@1234");
            Assert.assertEquals(getCurrentURL(), "https://rahulshettyacademy.com/client/#/dashboard/dash");
            landingPage.logOutApp();
            Assert.assertEquals(getCurrentURL(), "https://rahulshettyacademy.com/client/#/auth/login");
            System.out.println("Iteration: " + i);
        }
    }
}

