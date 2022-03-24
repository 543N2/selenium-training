package challenge.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BasePage {

    protected WebDriver driver;

    public BasePage(WebDriver driver){
        this.driver = driver;
    }

    public void typeText(String text, By locatorBy){
        driver.findElement(locatorBy).sendKeys(text);
    }



}
