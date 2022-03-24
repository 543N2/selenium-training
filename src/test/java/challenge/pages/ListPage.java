package challenge.pages;

import challenge.entities.Movie;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ListPage extends BasePage {

    private By movieButtonBy;

    public ListPage(WebDriver driver) { super(driver); }

    @Step("Clicks Movie option.")
    public MoviePage clickMovieButton(Movie movie){
        String locator = "a[href='/movie/"+ movie.getId() + "']";
        this.movieButtonBy = By.cssSelector(locator);
        driver.findElement(movieButtonBy).click();
        return new MoviePage(driver);
    }
}
