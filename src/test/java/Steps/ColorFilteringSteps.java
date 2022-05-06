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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ColorFilteringSteps {
    WebDriver webDriver = null;
    LoginPage loginPage;

    public void loginForColorFiltering(WebDriver webDriver, String email, String password){
        loginPage.getElementById("Email").clear();
        loginPage.getElementById("Password").clear();
        loginPage.getElementById("Email").sendKeys(email);
        loginPage.getElementById("Password").sendKeys(password);
        loginPage.getElementById("Password").sendKeys(Keys.ENTER);
    }

    public void colorFiltering(){
        webDriver.findElement(By.xpath("//*[@id=\"attribute-option-15\"]")).click();
    }

    @Before("@ColorFiltering")
    public void beforeColorFiltering(){
        String path = System.getProperty("user.dir") + "\\src\\main\\resources\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", path);
        webDriver = new ChromeDriver();
        loginPage = new LoginPage(webDriver);
        webDriver.manage().window().maximize();
    }

    @When("^user logs in with \"(.*)\" as a valid email and \"(.*)\" as a valid password then direct to the selected category then filter by red color$")
    public void loginAndColorFiltering(String email, String password){
        webDriver.navigate().to("https://demo.nopcommerce.com/login");
        loginForColorFiltering(webDriver, email, password);
        webDriver.navigate().to("https://demo.nopcommerce.com/shoes");
        colorFiltering();
    }

    @Then("Only items with red color should be displayed")
    public void validColorFiltering(){
        WebElement redShoesOnly = webDriver.findElement(By.xpath("/html/body/div[6]/div[3]/div/div[3]/div/div[2]/div[2]/div[2]/div/div/div/div/div[2]/h2/a"));
        Assert.assertTrue(redShoesOnly.isDisplayed());
    }

    @After("@ColorFiltering")
    public void exitColorFiltering(){
        webDriver.quit();
    }
}


