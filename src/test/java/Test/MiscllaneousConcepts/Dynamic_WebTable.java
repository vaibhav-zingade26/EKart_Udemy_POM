package Test.MiscllaneousConcepts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

// For Chrome process get value of CPU load.

//tbody//tr//td[1] ---chrome
//thead//tr//th[4]----CPU
public class Dynamic_WebTable {

    public int indexOfString(List<WebElement> ele,String name){
        int index=0;
        for(int i=0;i<ele.size();i++){
            if(ele.get(i).getText().contains(name)){
                index=i+1;
            }
        }
        return index;
    }
    public void hardWait(WebDriver driver,WebElement ele){
        WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(ele));
    }



    WebDriver driver;

    @Test
    public void Test01(){
        driver= new ChromeDriver();
        driver.get("https://practice.expandtesting.com/dynamic-table");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        List<WebElement> name= driver.findElements(By.xpath("//tbody//tr//td[1]"));
        List<WebElement> index= driver.findElements(By.xpath("//thead//tr//th"));
        /*System.out.println(indexOfString(index,"CP"));
        System.out.println(indexOfString(name,"Chrome"));*/
        int column=indexOfString(index,"CP");
        int row=indexOfString(name,"Chrome");
        WebElement desiredEle= driver.findElement(By.xpath("//tbody//tr["+column+"]//td["+row+"]"));
        System.out.println(desiredEle.getText()+"row :"+row+" column :"+column);
        WebElement desiredEle1= driver.findElement(By.xpath("//tbody//tr["+row+"]//td[contains(text(),'%')]"));
        System.out.println(desiredEle1.getText());




    }
}
