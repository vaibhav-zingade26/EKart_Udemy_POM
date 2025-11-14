package Test;

import BasePkg.Base;
import PageObjects.LandingPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class errorValidations extends Base {

    @Test
    public void TC01() throws InterruptedException, IOException {
        LandingPage landingPage=launchApplication();
        landingPage.loginApp("vaibhav26@gmail.com","dwdwgd");
        Assert.assertEquals("Incorrect email or password.",landingPage.errorMSg());


    }
}
