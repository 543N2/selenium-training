package challenge.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class LandingPage extends BasePage{

    private final By loginMainBy = By.cssSelector("a[href='/login']");
    private final By searchButtonBy = By.cssSelector("header a.search>span");
    private final By searchBarBy = By.cssSelector("div.search_bar form#search_form input#search_v4");
    private final By moviesLinkBy = By.cssSelector("header div.sub_media ul.navigation>li>a[href='/movie']");
    private final By topRatedButtonBy = By.cssSelector("a[href='/movie/top-rated']");
    private final By nowPlayingButtonBy = By.cssSelector("a[href='/movie/now-playing']");


    public LandingPage(WebDriver driver){
        super(driver);
    }

    public LoginPage clickLoginButton(){
        driver.findElement(loginMainBy).click();
        return new LoginPage(driver);
    }

    public LandingPage clickSearchIconButton(){
        driver.findElement(searchButtonBy).click();
        return new LandingPage(driver);
    }

    public CatalogPage typeMovieTitleAndSend(String movieTitle){
        driver.findElement(searchBarBy).sendKeys(movieTitle + Keys.RETURN);
        return new CatalogPage(driver);
    }

    public LandingPage hoverMoviesLink(){
        WebElement moviesLink = driver.findElement(moviesLinkBy);
        Actions action = new Actions(driver);
        action.moveToElement(moviesLink).build().perform();
        return new LandingPage(driver);
    }

    public CatalogPage clickTopRatedOption(){
        driver.findElement(topRatedButtonBy).click();
        return new CatalogPage(driver);
    }

    public CatalogPage clickNowPlayingOption(){
        driver.findElement(nowPlayingButtonBy).click();
        return new CatalogPage(driver);
    }
}
