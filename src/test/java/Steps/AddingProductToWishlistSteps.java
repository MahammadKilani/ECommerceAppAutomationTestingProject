package Steps;

import Pages.LoginPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class AddingProductToWishlistSteps {
    WebDriver webDriver = null;
    Select select = null;
    LoginPage loginPage;

    public void loginForWishlist(WebDriver webDriver, String email, String password){
        loginPage.getElementById("Email").clear();
        loginPage.getElementById("Password").clear();
        loginPage.getElementById("Email").sendKeys(email);
        loginPage.getElementById("Password").sendKeys(password);
        loginPage.getElementById("Password").sendKeys(Keys.ENTER);
    }

    public void wishlist(){
        select.selectByVisibleText("8");
        webDriver.findElement(By.id("add-to-wishlist-button-25")).click();
        webDriver.navigate().to("https://demo.nopcommerce.com/wishlist");
    }

    @Before("@Wishlist")
    public void beforeWishlist(){
        String path = System.getProperty("user.dir") + "\\src\\main\\resources\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", path);
        webDriver = new ChromeDriver();
        loginPage = new LoginPage(webDriver);
        webDriver.manage().window().maximize();
    }

    @When("^user logs in with \"(.*)\" as a valid email and \"(.*)\" as a valid password then direct to the selected item then clicks on add to wishlist for the wanted item$")
    public void loginAndWishlist(String email, String password){
        webDriver.navigate().to("https://demo.nopcommerce.com/login");
        loginForWishlist(webDriver, email, password);
        webDriver.navigate().to("https://demo.nopcommerce.com/adidas-consortium-campus-80s-running-shoes");
        select = new Select(webDriver.findElement(By.xpath("/html/body/div[6]/div[3]/div/div[2]/div/div/form/div[2]/div[1]/div[2]/div[6]/dl/dd[1]/select")));
        wishlist();
    }

    @Then("selected item should be added to the wishlist")
    public void validWishlist(){
       Assert.assertTrue(webDriver.findElement(By.xpath("/html/body/div[6]/div[3]/div/div/div/div[2]/div/form/div[1]/table/tbody/tr/td[3]/a")).isDisplayed());
    }

    @After("@Wishlist")
    public void exitWishlist(){
        webDriver.quit();
    }
}
