package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RegistrationPage {
    WebDriver webDriver;

    public RegistrationPage(WebDriver webDriver){
        this.webDriver = webDriver;
    }

    public WebElement getElementById(String element){
        return webDriver.findElement(By.id(element));
    }
}
