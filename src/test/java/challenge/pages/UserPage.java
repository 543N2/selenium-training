package challenge.pages;

import challenge.User;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UserPage extends BasePage{

    private By usernameDisplayBy = By.cssSelector("div[class='about'] a[href='/u/"+ User.getUsername() +"']");

    public UserPage(WebDriver driver){
        super(driver);
    }

    @Step("Reads username.")
    public String readUsername(){
        return driver.findElement(usernameDisplayBy).getText();
    }
}
