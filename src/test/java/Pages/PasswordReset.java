package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PasswordReset {
    WebDriver webDriver;
    public PasswordReset(WebDriver webDriver){
        this.webDriver = webDriver;
    }

    public WebElement getElementById(String element){
        return webDriver.findElement(By.id(element));
    }

    public WebElement getElementByClass(String element){
        return webDriver.findElement(By.className(element));
    }

    public WebElement submitButton(){
        return webDriver.findElement(By.cssSelector("button[name=\"send-email\"]"));
    }
}
