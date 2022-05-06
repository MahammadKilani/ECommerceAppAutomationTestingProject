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
import org.openqa.selenium.interactions.Actions;

public class CategorySelectionSteps {
    WebDriver webDriver = null;
    Actions action = null;
    LoginPage loginPage;

    public void loginForCategorySelection(WebDriver webDriver, String email, String password){
        loginPage.getElementById("Email").clear();
        loginPage.getElementById("Password").clear();
        loginPage.getElementById("Email").sendKeys(email);
        loginPage.getElementById("Password").sendKeys(password);
        loginPage.getElementById("Password").sendKeys(Keys.ENTER);
    }

    public void categorySelection(){
        WebElement category = webDriver.findElement(By.xpath("/html/body/div[6]/div[2]/ul[1]/li[3]/a"));
        WebElement subCategory = webDriver.findElement(By.xpath("/html/body/div[6]/div[2]/ul[1]/li[3]/ul/li[1]/a"));
        action = new Actions(webDriver);
        action.moveToElement(category);
        action.moveToElement(subCategory);
        action.click().build().perform();
    }

    @Before("@CategorySelection")
    public void beforeCategorySelection(){
        String path = System.getProperty("user.dir") + "\\src\\main\\resources\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", path);
        webDriver = new ChromeDriver();
        loginPage = new LoginPage(webDriver);
        webDriver.manage().window().maximize();
    }

    @When("^user logs in with \"(.*)\" as a valid email and \"(.*)\" as a valid password then selects random category then hover and open sub-Category$")
    public void loginAndCategorySelection(String email, String password){
        webDriver.navigate().to("https://demo.nopcommerce.com/login");
        loginForCategorySelection(webDriver, email, password);
        webDriver.navigate().to("https://demo.nopcommerce.com");
        categorySelection();

    }

    @Then("user should be able to see the contents of sub-Category on its webpage")
    public void validCategorySelection(){
        Assert.assertEquals("https://demo.nopcommerce.com/shoes", webDriver.getCurrentUrl());
    }

    @After("@CategorySelection")
    public void exitCategorySelection(){
        webDriver.quit();
    }
}


