package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MainPage {
    WebDriver webDriver;

    public MainPage(WebDriver webDriver){
        this.webDriver = webDriver;
    }

    public WebElement getElementById(String element){
        return webDriver.findElement(By.id(element));
    }

    public WebElement item(){
        return webDriver.findElement(By.className("product-item"));
    }

    public WebElement getElementByCssSelector(String element){
        return webDriver.findElement(By.cssSelector(element));
    }


}
