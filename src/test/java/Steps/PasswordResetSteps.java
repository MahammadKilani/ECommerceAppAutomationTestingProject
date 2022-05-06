package Steps;

import Pages.PasswordReset;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class PasswordResetSteps {
    WebDriver webDriver = null;
    PasswordReset passwordReset;

    public void reset(WebDriver webDriver, String email){
        passwordReset.getElementById("Email").clear();
        passwordReset.getElementById("Email").sendKeys(email);
        passwordReset.submitButton().click();
    }

    @Before("@PasswordReset")
    public void beforeResetting(){
        String path = System.getProperty("user.dir") + "\\src\\main\\resources\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", path);
        webDriver = new ChromeDriver();
        passwordReset = new PasswordReset(webDriver);
        webDriver.manage().window().maximize();
        webDriver.navigate().to("https://demo.nopcommerce.com/passwordrecovery");
    }

    @When("^user enters \"(.*)\" in Email and clicks on reset button$")
    public void entryLogic(String email){
        reset(webDriver, email);
    }

    @Then("message \"Enter your email\" should displayed")
    public void emptyFields() {
        String expectedEmail = "Enter your email";
        String actualEmail = passwordReset.getElementById("Email-error").getText();
        Assert.assertTrue(actualEmail.contains(expectedEmail));
    }

    @Then("message \"Email with instructions has been sent to you.\" should displayed")
    public void validEmail(){
        String expectedEmail = "Email with instructions has been sent to you";
        String actualEmail = passwordReset.getElementByClass("content").getText();
        Assert.assertTrue(actualEmail.contains(expectedEmail));
    }

    @After("@PasswordReset")
    public void exitLogin(){
        webDriver.quit();
    }

}
