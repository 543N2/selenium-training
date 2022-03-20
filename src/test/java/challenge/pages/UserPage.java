package challenge.pages;

import challenge.User;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UserPage extends BasePage{

    private By usernameDisplayBy;

    public UserPage(WebDriver driver){
        super(driver);
    }

    @Step("Reads username.")
    public String readUsername(User user){
        String locator = "div[class='about'] a[href='/u/"+ user.getUsername() +"']";
        this.usernameDisplayBy = By.cssSelector(locator);
        return driver.findElement(usernameDisplayBy).getText();
    }

}
