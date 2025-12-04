import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;


/*
 * username=vaibhav26@gmail.com
 * pwd=VacZ@1234
 *
 * id="userEmail"  id="userPassword"  id="login"
 * */

public class statndaloneTest {

    ExtentReports extent;

    @BeforeTest
    public void config() {
        //extentreports extentsparkreports
            String path = System.getProperty("user.dir") + "\\Reports\\index.html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
        reporter.config().setDocumentTitle("Standalone Report");
        reporter.config().setReportName("StandAloneReport");

        extent = new ExtentReports();
        extent.attachReporter(reporter);
    }

    @Test
    public void tc01() throws IOException {
        //extentreports extentsSpark
        ExtentTest t = extent.createTest("TC01");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        // driver.get("https://rahulshettyacademy.com/client");
        driver.get("https://demoqa.com/webtables");
        List<WebElement> header = driver.findElements(By.xpath("div.rt-resizable-header-content"));
        /* for(WebElement a:header){
            System.out.print(a.getText()+" ");
        }*/
        String sal = driver.findElement(By.xpath("//div[contains(text(),'Cierra')]/following-sibling::div[3]")).getText();
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String ssPath = System.getProperty("user.dir") + "\\Reports\\" + "standalone" + ".png";
        FileUtils.copyFile(source, new File(ssPath));
        t.addScreenCaptureFromPath(ssPath);
        t.fail("failing");
        extent.flush();








     /*   LandingPage landingPage = new LandingPage(driver);
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
        driver.findElement(By.cssSelector("[routerlink*='cart']")).click();*/
        //boolean b=driver.findElement(By.cssSelector("cartSection h3")).isDisplayed();
        //Assert.assertTrue(b);
        ////input[@placeholder='Select Country']
        //(//button[@class='btn btn-primary'])[4]

       /* Actions a= new Actions(driver);
        a.scrollToElement(driver.findElement(By.cssSelector(".totalRow button")));*/

        /*Zoom out */
       /* JavascriptExecutor jse = (JavascriptExecutor) driver;
        Thread.sleep(100);
        // Zoom out the page to 50%
        //System.out.println("Zooming out to 50%");
        jse.executeScript("document.body.style.zoom='70%'");
        Thread.sleep(100);*/

      /*  WebDriverWait w= new WebDriverWait(driver,Duration.ofSeconds(10));
        w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".totalRow button")));*/

        //driver.findElement(By.cssSelector(".totalRow button")).click();
        /*driver.findElement(By.xpath("//button[normalize-space()='Checkout']")).click();
        driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys("India");
        w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
        driver.findElement(By.cssSelector(".ta-results button:nth-of-type(2)")).click();
        driver.findElement(By.xpath("//a[@class='btnn action__submit ng-star-inserted']")).click();
        String confirmMsg=driver.findElement(By.cssSelector(".hero-primary")).getText();
        Assert.assertEquals(confirmMsg,"THANKYOU FOR THE ORDER.");

        driver.findElement(By.cssSelector("[routerlink*='myorders']")).click();
        List<WebElement> orderedProducts=driver.findElements(By.cssSelector("tr td:nth-child(3)"));*/


    }

    @Test
    public void dd(){

        ArrayList<String > p= new ArrayList<>();
        p.add("Abhay");
        p.add("add");
        System.out.println(p.contains("add"));
        System.out.println(p.contains("Abhay"));

    }
}
