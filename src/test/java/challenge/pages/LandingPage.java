package challenge.pages;

import io.qameta.allure.Step;
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

    @Step("Clicks login button on home page.")
    public LoginPage clickLoginButton(){
        driver.findElement(loginMainBy).click();
        return new LoginPage(driver);
    }

    @Step("Clicks search icon button on the top right of home page.")
    public LandingPage clickSearchIconButton(){
        driver.findElement(searchButtonBy).click();
        return new LandingPage(driver);
    }

    @Step("Types the movie title: {0} into the search bar and press Enter key.")
    public CatalogPage typeMovieTitleAndSend(String movieTitle){
        driver.findElement(searchBarBy).sendKeys(movieTitle + Keys.RETURN);
        return new CatalogPage(driver);
    }

    @Step("Hovers the movies option in the header.")
    public LandingPage hoverMoviesLink(){
        WebElement moviesLink = driver.findElement(moviesLinkBy);
        Actions action = new Actions(driver);
        action.moveToElement(moviesLink).build().perform();
        return new LandingPage(driver);
    }

    @Step("Click the Top-Rated movies option.")
    public CatalogPage clickTopRatedOption(){
        driver.findElement(topRatedButtonBy).click();
        return new CatalogPage(driver);
    }

    @Step("Clicks Now-playing movies option.")
    public CatalogPage clickNowPlayingOption(){
        driver.findElement(nowPlayingButtonBy).click();
        return new CatalogPage(driver);
    }
}
