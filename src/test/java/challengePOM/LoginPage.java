package challengePOM;

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

    public String password() { return password; }

    public By usernameInputBy() { return usernameInputBy; }

    public By passwordInputBy() { return passwordInputBy; }

    public String fakeUsername() { return fakeUsername; }

    public String fakePassword() { return fakePassword; }

    public By loginButtonBy() { return loginButtonBy; }

    public String redColor(){ return redColor; }

    public LoginPage typeUsername(){
        driver.findElement(usernameInputBy).sendKeys(username);
        return new LoginPage(driver);
    }

    public LoginPage typeFakeUsername(){
        driver.findElement(usernameInputBy).sendKeys(fakeUsername);
        return new LoginPage(driver);
    }

    public LoginPage typePassword(){
        driver.findElement(passwordInputBy).sendKeys(password);
        return new LoginPage(driver);
    }

    public LoginPage typeFakePassword(){
        driver.findElement(passwordInputBy).sendKeys(fakePassword);
        return new LoginPage(driver);
    }

    public String readBoxColor(){
        return driver.findElement(redErrorBoxBy).getCssValue("background-color");
    }

    public UserPage clickLoginButton(){
        driver.findElement(loginButtonBy).click();
        return new UserPage(driver);
    }

    public String readErrorMessage(){
        return driver.findElement(redErrorMessageBy).getText().replace("&nbsp;","");
    }

    public LoginPage clickLoginButtonToFail(){
        driver.findElement(loginButtonBy).click();
        return new LoginPage(driver);
    }

    public Boolean numberOfMessages() {
        if(driver.findElements(twoErrorMessagesBy).size() == 2)
            return true;
        return false;
    }


}
