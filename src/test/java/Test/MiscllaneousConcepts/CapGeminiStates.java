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

}
