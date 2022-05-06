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


public class SearchingSteps {
    WebDriver webDriver = null;
    LoginPage loginPage;
    MainPage mainPage;

    public void loginForSearch(WebDriver webDriver, String email, String password){
        loginPage.getElementById("Email").clear();
        loginPage.getElementById("Password").clear();
        loginPage.getElementById("Email").sendKeys(email);
        loginPage.getElementById("Password").sendKeys(password);
        loginPage.getElementById("Password").sendKeys(Keys.ENTER);
    }

    public void searching(WebDriver webDriver,String input){
        mainPage.getElementById("small-searchterms").clear();
        mainPage.getElementById("small-searchterms").sendKeys(input);
        mainPage.getElementById("small-searchterms").sendKeys(Keys.ENTER);
    }

    @Before("@Searching")
    public void beforeSearching(){
        String path = System.getProperty("user.dir") + "\\src\\main\\resources\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", path);
        webDriver = new ChromeDriver();
        loginPage = new LoginPage(webDriver);
        mainPage = new MainPage(webDriver);
        webDriver.manage().window().maximize();
    }

    @When("^user logs in with \"(.*)\" as a valid email, \"(.*)\" as a valid password and \"(.*)\" as an input for the search field$")
    public void loginIn(String email, String password, String input){
        webDriver.navigate().to("https://demo.nopcommerce.com/login");
        loginForSearch(webDriver, email, password);
        webDriver.navigate().to("https://demo.nopcommerce.com");
        searching(webDriver, input);
    }


    @Then("search results should be displayed in the page")
    public void validSearchResult(){
        Assert.assertTrue(mainPage.item().isDisplayed());
    }

    @After("@Searching")
    public void exitSearching(){
        webDriver.quit();
    }
}
