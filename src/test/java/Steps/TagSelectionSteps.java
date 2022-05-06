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


public class TagSelectionSteps {
    WebDriver webDriver = null;
    LoginPage loginPage;

    public void loginForTagSelection(WebDriver webDriver, String email, String password){
        loginPage.getElementById("Email").clear();
        loginPage.getElementById("Password").clear();
        loginPage.getElementById("Email").sendKeys(email);
        loginPage.getElementById("Password").sendKeys(password);
        loginPage.getElementById("Password").sendKeys(Keys.ENTER);
    }

    public void tagSelection(){
        webDriver.findElement(By.cssSelector("a[href=\"/cool\"]")).click();
    }

    @Before("@TagSelection")
    public void beforeTagSelection(){
        String path = System.getProperty("user.dir") + "\\src\\main\\resources\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", path);
        webDriver = new ChromeDriver();
        loginPage = new LoginPage(webDriver);
        webDriver.manage().window().maximize();
    }

    @When("^user logs in with \"(.*)\" as a valid email and \"(.*)\" as a valid password then direct to the selected category then clicks on cool tag$")
    public void loginAndTagSelection(String email, String password){
        webDriver.navigate().to("https://demo.nopcommerce.com/login");
        loginForTagSelection(webDriver, email, password);
        webDriver.navigate().to("https://demo.nopcommerce.com/shoes");
        tagSelection();
    }

    @Then("user should be navigated to a page with products tagged cool")
    public void validTagSelection(){
        Assert.assertEquals("https://demo.nopcommerce.com/cool", webDriver.getCurrentUrl());
    }

    @After("@TagSelection")
    public void exitTagSelection(){
        webDriver.quit();
    }
}


