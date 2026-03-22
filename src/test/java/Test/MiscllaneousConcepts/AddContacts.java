package Test.MiscllaneousConcepts;

import com.sun.source.tree.TryTree;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;

public class AddContacts {
    WebDriver driver;

    public void ById(String id,String value){
        driver.findElement(By.id(id)).sendKeys(value);
    }

    public void ById(String id){
        driver.findElement(By.id(id)).click();
    }

    @Test(dataProvider = "fillContacts")
    public void tc01(String firstName,String lastName,String birthdate,String email,String phone,String street1,String street2,String city,String stateProvince,String postalCode,String country){
        driver= new ChromeDriver();
        driver.get("https://thinking-tester-contact-list.herokuapp.com/");
        driver.manage().window().maximize();
        driver.findElement(By.id("email")).sendKeys("tes3t@fake.com");
        driver.findElement(By.id("password")).sendKeys("myPassword");
        driver.findElement(By.id("submit")).sendKeys(Keys.ENTER);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        //validate now the url content "contactList"
        try {
            String currentURL = driver.getCurrentUrl();
            //Assert.assertEquals(currentURL, "https://thinking-tester-contact-list.herokuapp.com/contactList");
        }catch (Exception e){
            System.out.println(e);
        }

        /// now add contacts

        driver.findElement(By.xpath("//button[contains(text(),'New')]")).click();

        //verify currrnURL
        try {
            String url=driver.getCurrentUrl();
            //Assert.assertEquals(url,"https://thinking-tester-contact-list.herokuapp.com/addContact");
        }catch (Exception e){
            System.out.println(e);
        }

        //add details
        ById("firstName",firstName);
        ById("lastName",lastName);
        ById("birthdate",birthdate);
        ById("email",email);
        ById("phone",phone);
        ById("street1",street1);
        ById("street2",street2);
        ById("city",city);
        ById("stateProvince",stateProvince);
        ById("country",country);
        ById("postalCode",postalCode);
        ById("submit");

    }

@DataProvider
    public Object[][] fillContacts(){
        return new Object[][]
                {{"efge","gdfgd","1970-01-01"," jdoe@fake.com","8003555555","1 Main St","Apartment A","Anytgerown","KS","12345","USA"},
                        {"grge","gdfgd","1974-01-01"," jdhyroe@fake.com","1234567898","1 Maifrfn St","Apargtment A","Anyhrttgerown","K3S","12f3345","USrA"},
                        {"utjty","gdfgd","1974-01-01"," jdoeh@fake.com","8003555544","1 Maigren St","Apartmhent A","Anytgee3rown","KSf","1233r45","USrA"}};
}

    }

