package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    WebDriver webDriver;

    public LoginPage(WebDriver webDriver){
        this.webDriver = webDriver;
    }

    public WebElement getElementById(String element){
        return webDriver.findElement(By.id(element));
    }
    public WebElement getElementByClass(String element){
        return webDriver.findElement(By.className(element));
    }
    public WebElement buttonLogout(){
        return webDriver.findElement(By.cssSelector("a[href=\"/logout\"]"));
    }
    public WebElement buttonAccount(){
        return webDriver.findElement(By.cssSelector("a[href=\"/customer/info\"]"));
    }


}
