package challenge.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class MoviePage extends BasePage{

    private final By genresBy = By.cssSelector("section#original_header span.genres>a");
    private final By movieTitleBy = By.cssSelector("div.header_poster_wrapper section > div > h2 > a");
    private final By castBy = By.cssSelector("#cast_scroller>ol>li");

    public MoviePage(WebDriver driver) {
        super(driver);
    }

    @Step("Validates if the movie has Action in its genres.")
    public Boolean isActionMovie(){
        List<WebElement> genres = driver.findElements(genresBy);
        for (WebElement g : genres) if (g.getText().equals("Action")) return true;
        return false;
    }

    @Step("Reads content of movie title.")
    public String readMovieTitleToCompare(){
        return driver.findElement(movieTitleBy).getText();
    }

    @Step("Implicitly waiting")
    public MoviePage waitImplicit(){
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        return new MoviePage(driver);
    }

    @Step("Selects and actor from the top billed cast.")
    public ActorPage selectAnActor(){
        Random rand = new Random();
        List<WebElement> cast = driver.findElements(castBy);
        cast.get( rand.nextInt(0,cast.size()) ).click();
        return new ActorPage(driver);
    }
}
