package challengePOM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class BasePage {

    protected WebDriver driver;

    public BasePage(WebDriver driver){
        this.driver = driver;
    }

    public void typeText(String text, By locatorBy){
        driver.findElement(locatorBy).sendKeys(text);
    }



}
