package BasePkg;

import PageObjects.LandingPage;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class Base {
    public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
    WebDriver driver;
    public LandingPage landingPage;

    public WebDriver initilizeDriver() throws IOException {
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "//src//main//resources//GlobalData.properties");
        prop.load(fis);
        String browserName = prop.getProperty("browser");
        switch (browserName) {
            case "chrome" -> driver = new ChromeDriver();
            case "firefox" -> driver = new FirefoxDriver();
            case "edge" -> driver = new EdgeDriver();
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return driver;
    }

    public List<HashMap<String, String>> getJsonIntoHashMap(String s) throws IOException {
        // read json to string
        String jsonString = FileUtils.readFileToString(new File(s), StandardCharsets.UTF_8);
        //string to maps using Jackson databind
        ObjectMapper obj = new ObjectMapper();
        List<HashMap<String, String>> map = obj.readValue(jsonString, new TypeReference<List<HashMap<String, String>>>() {
        });
        return map;
    }

    @BeforeMethod(groups = "Purchase")
    public void launchApplication() throws IOException {
        initilizeDriver();
        landingPage = new LandingPage(driver);
        landingPage.goTo();
    }

    @AfterMethod(groups = "Purchase")
    public void tearDown() {
        driver.quit();
    }
}
