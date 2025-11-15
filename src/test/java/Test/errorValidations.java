package Test;

import BasePkg.Base;
import PageObjects.LandingPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class errorValidations extends Base {

    @Test
    public void TC01() throws InterruptedException, IOException {
        LandingPage landingPage = launchApplication();
        landingPage.loginApp("vaibhav26@gmail.com", "dwdwgd");
        Assert.assertEquals("Incorrect email or password.", landingPage.errorMSg());
    }

    @Test
    public void TC02() throws IOException, InterruptedException {
        //id email id is wrong and pwd is correct "*Enter Valid Email" this string validate
        LandingPage landingPage = launchApplication();
        landingPage.loginApp("vaibhav26.com", "VacZ@1234");
        Assert.assertEquals("*Enter Valid Email", landingPage.emailWrongValidations());
    }
}

