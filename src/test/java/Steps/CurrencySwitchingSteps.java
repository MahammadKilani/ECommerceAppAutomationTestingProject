package Steps;

import Pages.LoginPage;
import Pages.MainPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class CurrencySwitchingSteps {
    WebDriver webDriver = null;
    Select select = null;
    LoginPage loginPage;
    MainPage mainPage;

    public void loginForCurrencySwitch(WebDriver webDriver, String email, String password){
        loginPage.getElementById("Email").clear();
        loginPage.getElementById("Password").clear();
        loginPage.getElementById("Email").sendKeys(email);
        loginPage.getElementById("Password").sendKeys(password);
        loginPage.getElementById("Password").sendKeys(Keys.ENTER);
    }

    public void currencySwitching(WebDriver webDriver){
        select.selectByVisibleText("Euro");
    }

    @Before("@CurrencySwitch")
    public void beforeSwitching(){
        String path = System.getProperty("user.dir") + "\\src\\main\\resources\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", path);
        webDriver = new ChromeDriver();
        loginPage = new LoginPage(webDriver);
        mainPage = new MainPage(webDriver);
        webDriver.manage().window().maximize();
    }

    @When("^user logs in with \"(.*)\" as a valid email and \"(.*)\" as a valid password then selects the Euro currency$")
    public void loginAndSwitch(String email, String password){
        webDriver.navigate().to("https://demo.nopcommerce.com/login");
        loginForCurrencySwitch(webDriver, email, password);
        webDriver.navigate().to("https://demo.nopcommerce.com");
        select = new Select(loginPage.getElementById("customerCurrency"));
        currencySwitching(webDriver);
    }


    @Then("selected currency should be displayed in the page")
    public void validSwitching(){
        String expectedCurrency = "Euro";
        String actualCurrency = mainPage.getElementByCssSelector("option[selected]").getText();
        Assert.assertTrue(actualCurrency.contains(expectedCurrency));
    }

    @After("@CurrencySwitch")
    public void exitSwitching(){
        webDriver.quit();
    }
}
