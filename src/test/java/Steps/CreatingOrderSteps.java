package Steps;

import Pages.LoginPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class CreatingOrderSteps {
    WebDriver webDriver = null;
    Select select1 = null;
    Select select2 = null;
    LoginPage loginPage;

    public void loginForCreatingOrder(WebDriver webDriver, String email, String password){
        loginPage.getElementById("Email").clear();
        loginPage.getElementById("Password").clear();
        loginPage.getElementById("Email").sendKeys(email);
        loginPage.getElementById("Password").sendKeys(password);
        loginPage.getElementById("Password").sendKeys(Keys.ENTER);
    }

    public void creatingOrder() throws InterruptedException{
        webDriver.findElement(By.id("BillingNewAddress_FirstName")).clear();
        webDriver.findElement(By.id("BillingNewAddress_FirstName")).sendKeys("adam");
        webDriver.findElement(By.id("BillingNewAddress_LastName")).clear();
        webDriver.findElement(By.id("BillingNewAddress_LastName")).sendKeys("adam");
        webDriver.findElement(By.id("BillingNewAddress_Email")).clear();
        webDriver.findElement(By.id("BillingNewAddress_Email")).sendKeys("adam@adam.com");
        select2 = new Select(webDriver.findElement(By.id("BillingNewAddress_CountryId")));
        select2.selectByVisibleText("Egypt");
        webDriver.findElement(By.id("BillingNewAddress_City")).clear();
        webDriver.findElement(By.id("BillingNewAddress_City")).sendKeys("Alexandria");
        webDriver.findElement(By.id("BillingNewAddress_Address1")).clear();
        webDriver.findElement(By.id("BillingNewAddress_Address1")).sendKeys("Somuha");
        webDriver.findElement(By.id("BillingNewAddress_ZipPostalCode")).clear();
        webDriver.findElement(By.id("BillingNewAddress_ZipPostalCode")).sendKeys("21570");
        webDriver.findElement(By.id("BillingNewAddress_PhoneNumber")).clear();
        webDriver.findElement(By.id("BillingNewAddress_PhoneNumber")).sendKeys("01553570850");
        webDriver.findElement(By.xpath("//*[@id=\"billing-buttons-container\"]/button[4]")).click();
        Thread.sleep(5000);
        webDriver.findElement(By.xpath("//*[@id=\"shipping-method-buttons-container\"]/button")).click();
        Thread.sleep(5000);
        webDriver.findElement(By.xpath("//*[@id=\"payment-method-buttons-container\"]/button")).click();
        Thread.sleep(5000);
        webDriver.findElement(By.xpath("//*[@id=\"payment-info-buttons-container\"]/button")).click();
        Thread.sleep(5000);
        webDriver.findElement(By.xpath("//*[@id=\"confirm-order-buttons-container\"]/button")).click();
        Thread.sleep(10000);
    }

    @Before("@CreatingOrder")
    public void beforeCreatingOrder(){
        String path = System.getProperty("user.dir") + "\\src\\main\\resources\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", path);
        webDriver = new ChromeDriver();
        loginPage = new LoginPage(webDriver);
        webDriver.manage().window().maximize();
    }

    @When("^user logs in with \"(.*)\" as a valid email and \"(.*)\" as a valid password$")
    public void loginForCreatingOrder(String email, String password){
        webDriver.navigate().to("https://demo.nopcommerce.com/login");
        loginForCreatingOrder(webDriver, email, password);

    }

    @And("direct to the selected item then clicks on ADD TO CART for the wanted item")
    public void selectingItem(){
        webDriver.navigate().to("https://demo.nopcommerce.com/adidas-consortium-campus-80s-running-shoes");
        select1 = new Select(webDriver.findElement(By.xpath("/html/body/div[6]/div[3]/div/div[2]/div/div/form/div[2]/div[1]/div[2]/div[6]/dl/dd[1]/select")));
        select1.selectByVisibleText("8");
        webDriver.findElement(By.id("add-to-cart-button-25")).click();
    }

    @And("direct to the cart to proceed checkout")
    public void checkingOutFromCart(){
        webDriver.navigate().to("https://demo.nopcommerce.com/cart");
        webDriver.findElement(By.id("termsofservice")).click();
        webDriver.findElement(By.id("checkout")).click();
    }

    @And("complete all fields till order confirmed")
    public void fillingOrderDetails() throws InterruptedException{
        creatingOrder();
    }



    @Then("order should be successfully created")
    public void validCreatingOrder(){
        String expectedUrl = "https://demo.nopcommerce.com/checkout/completed";
        String successMessage = "Your order has been successfully processed!";
        Assert.assertEquals(expectedUrl, webDriver.getCurrentUrl());
        Assert.assertTrue(webDriver.findElement(By.xpath("/html/body/div[6]/div[3]/div/div/div/div[2]/div/div[1]/strong")).getText().contains(successMessage));
    }

    @After("@CreatingOrder")
    public void exitCreatingOrder(){
        webDriver.quit();
    }
}
