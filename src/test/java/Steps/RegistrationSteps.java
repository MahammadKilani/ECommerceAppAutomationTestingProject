package Steps;

import Pages.RegistrationPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class RegistrationSteps {
    WebDriver webDriver = null;
    RegistrationPage registrationPage;

    public void registration(WebDriver webDriver, String firstName, String lastName, String email, String password, String confirmPassword){
        registrationPage.getElementById("FirstName").clear();
        registrationPage.getElementById("LastName").clear();
        registrationPage.getElementById("Email").clear();
        registrationPage.getElementById("Password").clear();
        registrationPage.getElementById("ConfirmPassword").clear();
        registrationPage.getElementById("FirstName").sendKeys(firstName);
        registrationPage.getElementById("LastName").sendKeys(lastName);
        registrationPage.getElementById("Email").sendKeys(email);
        registrationPage.getElementById("Password").sendKeys(password);
        registrationPage.getElementById("ConfirmPassword").sendKeys(confirmPassword);
        registrationPage.getElementById("register-button").click();
    }

    @Before("@Registration")
    public void beforeRegistration(){
        String path = System.getProperty("user.dir") + "\\src\\main\\resources\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", path);
        webDriver = new ChromeDriver();
        registrationPage = new RegistrationPage(webDriver);
        webDriver.manage().window().maximize();
        webDriver.navigate().to("https://demo.nopcommerce.com/register");
    }

    @When("^user enters \"(.*)\" in First Name, \"(.*)\" in Last Name, \"(.*)\" in Email, \"(.*)\" in Password and \"(.*)\" in Confirm Password$")
    public void entryLogic(String firstName, String lastName, String email, String password, String confirmPassword){
        registration(webDriver, firstName, lastName, email, password, confirmPassword);
    }

    @Then("user should not be able to register using empty fields, notification messages should appear to guide the user")
    public void emptyFields(){
        String expectedFirstName = "First name is required";
        String expectedLastName = "Last name is required";
        String expectedEmail = "Email is required";
        String expectedPassword = "Password is required";
        String expectedConfirmPassword = "Password is required";
        String actualFirstName = registrationPage.getElementById("FirstName-error").getText();
        String actualLastName = registrationPage.getElementById("LastName-error").getText();
        String actualEmail = registrationPage.getElementById("Email-error").getText();
        String actualPassword = registrationPage.getElementById("Password-error").getText();
        String actualConfirmPassword = registrationPage.getElementById("ConfirmPassword-error").getText();
        Assert.assertTrue(actualFirstName.contains(expectedFirstName));
        Assert.assertTrue(actualLastName.contains(expectedLastName));
        Assert.assertTrue(actualEmail.contains(expectedEmail));
        Assert.assertTrue(actualPassword.contains(expectedPassword));
        Assert.assertTrue(actualConfirmPassword.contains(expectedConfirmPassword));
        Assert.assertEquals("https://demo.nopcommerce.com/register", webDriver.getCurrentUrl());
    }

    @Then("user should not be able to register using invalid email, short password and unmatched confirmation, notification messages should appear to guide the user")
    public void invalidInputEmailandPassword(){
        String expectedEmail = "Wrong email";
        String expectedPassword = "must have at least 6 characters";
        String expectedConfirmPassword = "The password and confirmation password do not match";
        String actualEmail = registrationPage.getElementById("Email-error").getText();
        String actualPassword = registrationPage.getElementById("Password-error").getText();
        String actualConfirmPassword = registrationPage.getElementById("ConfirmPassword-error").getText();
        Assert.assertTrue(actualEmail.contains(expectedEmail));
        Assert.assertTrue(actualPassword.contains(expectedPassword));
        Assert.assertTrue(actualConfirmPassword.contains(expectedConfirmPassword));
        Assert.assertEquals("https://demo.nopcommerce.com/register", webDriver.getCurrentUrl());
    }

    @Then("user should be able to register using valid data")
    public void validRegistration(){
        String expectedResult = "Your registration completed";
        String actualResult = webDriver.findElement(By.className("result")).getText();
        Assert.assertTrue(actualResult.contains(expectedResult));
        Assert.assertEquals("https://demo.nopcommerce.com/registerresult/1", webDriver.getCurrentUrl());
    }

    @After("@Registration")
    public void exitRegistration(){
        webDriver.quit();
    }
}
