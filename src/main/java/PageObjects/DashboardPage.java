package PageObjects;

import Utilities.Base;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

public class DashboardPage extends Base {

    WebDriver driver;

    public DashboardPage(WebDriver driver){
        super(driver);
        this.driver= driver;
        PageFactory.initElements(driver,this);
    }

    //List<WebElement> cardBody=driver.findElements(By.cssSelector(".card-body"));
    @FindBy(css=".card-body")
    private List<WebElement> products;

    @FindBy(xpath = "//h5//b")
    private List<WebElement> productName;

    @FindBy(xpath = "//div[@class='py-2 border-bottom ml-3']//input[@name='search' and @type='text']")
    private WebElement Search;

    @FindBy(xpath = "//h5//b")
    private WebElement productAfterSeach;

    private final By productBy=By.cssSelector(".card-body");
    private final By addToCart=By.cssSelector(".card-body button:last-of-type");
    private final By productByName=By.xpath("//h5//b");




    public List<WebElement> getListOfProducts() {
        explicitWait(productBy);
        return products;

    }

    public List<WebElement> getListOfProductsName() {
        explicitWait(productByName);
        return productName;

    }


    public WebElement getProductByName(String productName){
        WebElement desiredProd=getListOfProducts().stream().filter(zara->
                zara.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
        return desiredProd;

    }

    public void addProductToCart(String productName) throws InterruptedException {
        zoomOutWebPage();
        WebElement desiredProd=getProductByName(productName);
        explicitWait(addToCart);
        desiredProd.findElement(addToCart).click();
    }

    public void addProductToCart(String productName,String productName1) throws InterruptedException {
        WebElement desiredProd=getProductByName(productName);
        desiredProd.findElement(addToCart).click();
        Thread.sleep(2000);
        WebElement desiredProd2=getProductByName(productName1);
        desiredProd2.findElement(addToCart).click();
    }

    public String getValuesofProduct(String product){
        return driver.findElement(By.xpath("//b[contains(text(),'"+product+"')]/parent::h5//following-sibling::div/div[contains(text(),'$')]")).getText();
    }

    public void search(){
        driver.findElement(By.xpath("//div[@class='py-2 border-bottom ml-3']//input[@formcontrolname='productName']")).sendKeys("2"+ Keys.ENTER);
    }

    public String errorMsg(){
       // explicitWait(By.xpath("div[aria-label='No Products Found']"));
        return driver.findElement(By.xpath("div[aria-label='No Products Found']")).getText();
    }

    public String priceOfProduct(String product){
        return driver.findElement(By.xpath("//b[text()='"+product+"']/ancestor::div[@class='card-body']//div[@class='text-muted']")).getText();
    }

    public void clickOnView(String product){
        explicitWait(productBy);
        //driver.findElement(By.xpath("//b[text()='"+product+"']/ancestor::div[@class='card-body']//button[contains(text(),'View')]")).click();
        //b[text()='iphone 13 pro']/ancestor::div[@class='card-body']//button[1]
        try {
            driver.findElement(By.xpath("//b[text()='"+product+"']/ancestor::div[@class='card-body']//button[1]")).click();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }


    public void verifyProductAndPrice(String prod,String p){
        String product = driver.findElement(By.xpath("//h2[text()='"+prod+"']")).getText();
        String price=driver.findElement(By.xpath("//h3[contains(text(),'$')]")).getText();
        System.out.print("price on dashboard is : "+price+" & ");
        System.out.print("price on view is : "+p+" ");
        System.out.println(" ");
        System.out.print("product on dashboard is : "+prod+" & ");
        System.out.print("product on view is : "+product+" ");
        System.out.println(" ");
        Assert.assertEquals(p,price);
        Assert.assertEquals(prod.toLowerCase(),product.toLowerCase());
    }

    public void searchProduct(String prod){
        //div[@class='py-2 border-bottom ml-3']//input[@name='search' and @type='text']
        //explicitWait(Search);
        Search.sendKeys(prod + Keys.ENTER);

    }

    public String productDisplayed(){
        try {
            explicitWait(productAfterSeach);
            return productAfterSeach.getText();
        }catch (StaleElementReferenceException e){
            explicitWait(productAfterSeach);
            return productAfterSeach.getText();
        }finally {
            Search.clear();
        }


    }
}




