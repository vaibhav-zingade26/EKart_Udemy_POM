package Test.MiscllaneousConcepts;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.net.http.WebSocket;
import java.time.Duration;

public class CapGeminiStates {

    WebDriver driver;


    public void selectByCountryName(WebDriver driver,WebElement ele,String country){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", ele);
        WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(ele));
        Select select=new Select(ele);
        try {
            select.selectByVisibleText(country);
            System.out.println("Yes, The Country is present");
        }catch (Exception e){
            System.out.println(country+" is not present");
        }

    }

    @Test
    public void TC01(){
        driver= new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to("https://practice.expandtesting.com/dropdown");
        WebElement country=driver.findElement(By.id("country"));
        selectByCountryName(driver,country,"India");
    }

    @Test
    public void alert(){
        driver= new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to("https://demo.automationtesting.in/Alerts.html");
        driver.findElement(By.id("OKTab")).click();
        driver.switchTo().alert().accept();

    }

    @Test
    public void frames(){
        driver= new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demo.automationtesting.in/Frames.html");
        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='singleframe']")));
        driver.findElement(By.xpath("//input[@type='text']")).sendKeys("Vaibhav");

        //now switch to default fra,e

        driver.switchTo().defaultContent();

        driver.findElement(By.xpath("//a[@href='#Multiple']")).click();

        //go to multiples frame

        WebElement frame1=driver.findElement(By.xpath("//iframe[@src='MultipleFrames.html']"));
        driver.switchTo().frame(frame1);

        //now go inside single frame
     /*   WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("//iframe[@src='SingleFrames.html']"));*/
        try {
         /*   WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(10));
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("//iframe[@src='SingleFrame.html']"));*/
            WebElement frame2=driver.findElement(By.xpath("//iframe[@src='SingleFrame.html']"));
            driver.switchTo().frame(frame2);
            driver.findElement(By.xpath("//input[@type='text']")).sendKeys("Vaibhav");
        }catch (Exception e){
            System.out.println("Frame is not present");
        }

        driver.switchTo().defaultContent();
        driver.findElement(By.xpath("//a[@href='Index.html']")).click();






    }

}
