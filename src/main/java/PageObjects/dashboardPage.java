package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class dashboardPage {

    //List<WebElement> cardBody=driver.findElements(By.cssSelector(".card-body"));
    @FindBy(css=".card-body")
    List<WebElement> cardBody;
}
