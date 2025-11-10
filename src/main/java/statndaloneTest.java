import PageObjects.LandingPage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;


/*
* username=vaibhav26@gmail.com
* pwd=VacZ@1234
*
* id="userEmail"  id="userPassword"  id="login"
* */

public class statndaloneTest {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://rahulshettyacademy.com/client");
        LandingPage landingPage = new LandingPage(driver);
        driver.findElement(By.id("userEmail")).sendKeys("vaibhav26@gmail.com");
        driver.findElement(By.id("userPassword")).sendKeys("VacZ@1234");
        driver.findElement(By.id("login")).click();

        WebDriverWait w= new WebDriverWait(driver,Duration.ofSeconds(10));
        w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".card-body")));

        List<WebElement>cardBody=driver.findElements(By.cssSelector(".card-body"));

        WebElement desiredProd=cardBody.stream().filter(zara->
                zara.findElement(By.cssSelector("b")).getText().equals("ZARA COAT 3")).findFirst().orElse(null);
        desiredProd.findElement(By.cssSelector(".card-body button:last-of-type")).click();

        Thread.sleep(1000);
        driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
       //boolean b=driver.findElement(By.cssSelector("cartSection h3")).isDisplayed();
        //Assert.assertTrue(b);
        ////input[@placeholder='Select Country']
        //(//button[@class='btn btn-primary'])[4]

       /* Actions a= new Actions(driver);
        a.scrollToElement(driver.findElement(By.cssSelector(".totalRow button")));*/

        /*Zoom out */
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        Thread.sleep(100);
        // Zoom out the page to 50%
        //System.out.println("Zooming out to 50%");
        jse.executeScript("document.body.style.zoom='70%'");
        Thread.sleep(100);

      /*  WebDriverWait w= new WebDriverWait(driver,Duration.ofSeconds(10));
        w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".totalRow button")));*/

        //driver.findElement(By.cssSelector(".totalRow button")).click();
        driver.findElement(By.xpath("//button[normalize-space()='Checkout']")).click();
        driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys("India");


        w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
        driver.findElement(By.cssSelector(".ta-results button:nth-of-type(2)")).click();
        driver.findElement(By.xpath("//a[@class='btnn action__submit ng-star-inserted']")).click();
        String confirmMsg=driver.findElement(By.cssSelector(".hero-primary")).getText();
        Assert.assertEquals(confirmMsg,"THANKYOU FOR THE ORDER.");




    }
}
