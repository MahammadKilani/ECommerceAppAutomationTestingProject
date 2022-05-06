package Steps;

import Pages.LoginPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginSteps {
    WebDriver webDriver = null;
    LoginPage loginPage;

    public void login(WebDriver webDriver, String email, String password){
        loginPage.getElementById("Email").clear();
        loginPage.getElementById("Password").clear();
        loginPage.getElementById("Email").sendKeys(email);
        loginPage.getElementById("Password").sendKeys(password);
        loginPage.getElementById("Password").sendKeys(Keys.ENTER);
    }

    @Before("@Login")
    public void beforeLogin(){
        String path = System.getProperty("user.dir") + "\\src\\main\\resources\\chromedriver.exe";
        System.setProperty("webdriver.chrome.diver", path);
        webDriver = new ChromeDriver();
        loginPage = new LoginPage(webDriver);
        webDriver.manage().window().maximize();
        webDriver.navigate().to("https://demo.nopcommerce.com/login");
    }

    @When("user inputs \"(.*)\" in Email and \"(.*)\" in Password$")
    public void entryLogic(String email, String password){
        login(webDriver, email, password);
    }

    @Then("user should not be able to login with empty email and empty password")
    public void emptyFields(){
        String expectedEmail = "Please enter your email";
        String actualEmail = loginPage.getElementById("Email-error").getText();
        Assert.assertTrue(actualEmail.contains(expectedEmail));
    }

    @Then("user should not be able to login with invalid email")
    public void invalidEmail(){
        String expectedEmail = "No customer account found";
        String actualEmail = loginPage.getElementByClass("message-error").getText();
        Assert.assertTrue(actualEmail.contains(expectedEmail));
        Assert.assertEquals("https://demo.nopcommerce.com/login", webDriver.getCurrentUrl());
    }

    @Then("user should not be able to login with invalid password")
    public void invalidPassword(){
        String expectedPassword = "The credentials provided are incorrect";
        String actualPassword = loginPage.getElementByClass("message-error").getText();
        Assert.assertTrue(actualPassword.contains(expectedPassword));
        Assert.assertEquals("https://demo.nopcommerce.com/login", webDriver.getCurrentUrl());
    }

    @Then("user should be able to login with valid email and valid password")
    public void validLogin(){
        Assert.assertTrue(loginPage.buttonLogout().isDisplayed());
        Assert.assertTrue(loginPage.buttonAccount().isDisplayed());
    }

    @After("@Login")
    public void exitLogin(){
        webDriver.quit();
    }
}
