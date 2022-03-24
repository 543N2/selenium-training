package challenge.pages;

import challenge.entities.List;
import challenge.entities.User;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UserPage extends BasePage{

    private By usernameButtonBy = By.cssSelector("header li.user span");
    private By usernameDisplayBy;
    private By listOptionBy;
    private By listButtonBy;

    public UserPage(WebDriver driver){
        super(driver);
    }

    @Step("Reads username.")
    public String readUsername(User user){
        String locator = "div[class='about'] a[href='/u/"+ user.getUsername() +"']";
        this.usernameDisplayBy = By.cssSelector(locator);
        return driver.findElement(usernameDisplayBy).getText();
    }

    @Step("Clicks username button.")
    public UserPage clickUsernameButton(){
        driver.findElement(usernameButtonBy).click();
        return new UserPage(driver);
    }

    @Step("Clicks List option.")
    public UserPage clickListOptionDropdown(User user){
        String locator = "div.k-widget a[href='/u/"+ user.getUsername() +"/lists']";
        this.listOptionBy = By.cssSelector(locator);
        driver.findElement(listOptionBy).click();
        return new UserPage(driver);
    }

    @Step("Clicks List option.")
    public ListPage clickListButton(List list){
        String locator = "a[href='/list/"+ list.getId() + "']";
        this.listButtonBy = By.cssSelector(locator);
        driver.findElement(listButtonBy).click();
        return new ListPage(driver);
    }
}
