package challenge.pages;

import challenge.User;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private final String username = User.getUsername();
    private final String password = User.getPassword();
    private final String fakeUsername = User.getFakeUsername();
    private final String fakePassword = User.getFakePassword();
    private final String redColor = "rgba(212, 2, 66, 1)";
    private final By usernameInputBy = By.id("username");
    private final By passwordInputBy = By.id("password");
    private final By loginButtonBy = By.id("login_button");
    private final By redErrorBoxBy = By.cssSelector("div[class='carton']>a>h2");
    private final By redErrorMessageBy = By.cssSelector("div[class='carton']>a>h2>span");
    private final By twoErrorMessagesBy = By.cssSelector("div.carton>div.content>ul>li");


    public LoginPage(WebDriver driver){ super(driver); }

    public String username() { return username; }

    public String redColor(){ return redColor; }

    @Step("Types username.")
    public LoginPage typeUsername(){
        driver.findElement(usernameInputBy).sendKeys(username);
        return new LoginPage(driver);
    }

    @Step("Types username.")
    public LoginPage typeFakeUsername(){
        driver.findElement(usernameInputBy).sendKeys(fakeUsername);
        return new LoginPage(driver);
    }

    @Step("Types username.")
    public LoginPage typePassword(){
        driver.findElement(passwordInputBy).sendKeys(password);
        return new LoginPage(driver);
    }

    @Step("Types username.")
    public LoginPage typeFakePassword(){
        driver.findElement(passwordInputBy).sendKeys(fakePassword);
        return new LoginPage(driver);
    }

    @Step("Reads the background color of the error text box.")
    public String readBoxColor(){
        return driver.findElement(redErrorBoxBy).getCssValue("background-color");
    }

    @Step("Clicks login button.")
    public UserPage clickLoginButton(){
        driver.findElement(loginButtonBy).click();
        return new UserPage(driver);
    }

    @Step("Read contents of red error message..")
    public String readErrorMessage(){
        return driver.findElement(redErrorMessageBy).getText().replace("&nbsp;","");
    }

    @Step("Clicks login button expecting to fail.")
    public LoginPage clickLoginButtonToFail(){
        driver.findElement(loginButtonBy).click();
        return new LoginPage(driver);
    }

    @Step("Counts number of error messages.")
    public Boolean numberOfMessages() {
        if(driver.findElements(twoErrorMessagesBy).size() == 2)
            return true;
        return false;
    }


}
