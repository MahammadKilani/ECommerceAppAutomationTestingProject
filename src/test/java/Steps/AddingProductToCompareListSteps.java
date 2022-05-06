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

public class AddingProductToCompareListSteps {
    WebDriver webDriver = null;
    LoginPage loginPage;

    public void loginForCompareList(WebDriver webDriver, String email, String password){
        loginPage.getElementById("Email").clear();
        loginPage.getElementById("Password").clear();
        loginPage.getElementById("Email").sendKeys(email);
        loginPage.getElementById("Password").sendKeys(password);
        loginPage.getElementById("Password").sendKeys(Keys.ENTER);
    }

    public void compareList() throws InterruptedException{
        webDriver.findElement(By.xpath("/html/body/div[6]/div[3]/div/div[3]/div/div[2]/div[2]/div[2]/div/div/div[1]/div/div[2]/div[3]/div[2]/button[2]")).click();
        Thread.sleep(5000);
        webDriver.navigate().to("https://demo.nopcommerce.com/compareproducts");
    }

    @Before("@CompareList")
    public void beforeCompareList(){
        String path = System.getProperty("user.dir") + "\\src\\main\\resources\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", path);
        webDriver = new ChromeDriver();
        loginPage = new LoginPage(webDriver);
        webDriver.manage().window().maximize();
    }

    @When("^user logs in with \"(.*)\" as a valid email and \"(.*)\" as a valid password then direct to the selected item then clicks on add to compare list for the wanted item$")
    public void loginAndCompareList(String email, String password) throws InterruptedException{
        webDriver.navigate().to("https://demo.nopcommerce.com/login");
        loginForCompareList(webDriver, email, password);
        webDriver.navigate().to("https://demo.nopcommerce.com/shoes");
        compareList();
    }

    @Then("selected item should be added to the compare list")
    public void validCompareList(){
       Assert.assertTrue(webDriver.findElement(By.xpath("/html/body/div[6]/div[3]/div/div[2]/div/div[2]/div/table/tbody/tr[2]/td[2]/a/img")).isDisplayed());
    }

    @After("@CompareList")
    public void exitCompareList(){
        webDriver.quit();
    }
}
