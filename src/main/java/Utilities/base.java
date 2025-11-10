package Utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class base {




    /*public void zoomOutWebPage() throws InterruptedException {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        Thread.sleep(100);
        // Zoom out the page to 50%
        //System.out.println("Zooming out to 50%");
        jse.executeScript("document.body.style.zoom='70%'");
        Thread.sleep(100);
    }*/

    public void explicitWait(){
        WebDriverWait w= new WebDriverWait(driver, Duration.ofSeconds(10));
        w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".card-body")));
    }
}
