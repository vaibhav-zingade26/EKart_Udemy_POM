import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class XpathPractice {
    String name="Vaibhav";
    WebDriver driver;


    public void addEntry(){
        driver.findElement(By.cssSelector("#addNewRecordButton")).click();
        driver.findElement(By.cssSelector("#firstName")).sendKeys(name);
        driver.findElement(By.cssSelector("#lastName")).sendKeys("daed");
        driver.findElement(By.cssSelector("#userEmail")).sendKeys("dewdw@gmail.com");
        driver.findElement(By.cssSelector("#age")).sendKeys("27");
        driver.findElement(By.cssSelector("#salary")).sendKeys("65000");
        driver.findElement(By.cssSelector("#department")).sendKeys("QA");
        driver.findElement(By.cssSelector("#submit")).click();
    }

    public boolean isNamePresent(){
        if(driver.findElement(By.xpath("//div[contains(text(),'"+name+"')]")).isDisplayed()){
            return true;
        }
        return false;
    }


@BeforeTest
public void intialiseDriver() throws InterruptedException {
    driver = new ChromeDriver();
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
    driver.get("https://demoqa.com/webtables");
    JavascriptExecutor jse = (JavascriptExecutor) driver;
    Thread.sleep(100);
    // Zoom out the page to 50%
    //System.out.println("Zooming out to 50%");
    jse.executeScript("document.body.style.zoom='50%'");
    Thread.sleep(100);
}
    @Test
    public void TC01() {

        List<WebElement> header = driver.findElements(By.xpath("div.rt-resizable-header-content"));

        for (WebElement a : header) {
            System.out.print(a.getText() + " ");
        }

        String sal = driver.findElement(By.xpath("//div[contains(text(),'Cierra')]/following-sibling::div[4]")).getText();
        System.out.println(sal);
       // driver.close();

    }

    @Test
    public void TC02(){
        addEntry();
        Assert.assertTrue(isNamePresent(),"Name is not displayed into table");

    }


}
