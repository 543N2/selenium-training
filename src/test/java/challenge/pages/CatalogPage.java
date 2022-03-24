package challenge.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Random;

public class CatalogPage extends BasePage {

    private final By searchResultsBy = By.cssSelector("section.panel div.search_results.movie>div.results.flex div.wrapper div.title h2");
    private final By filtersButtonBy = By.cssSelector("div.filter_panel>div.name");
    private final By actionButtonBy = By.cssSelector("#with_genres>li>a[href*='with_genres=28']");
    private final By movieFilter = By.cssSelector("#page_1 div.content h2>a[href='/movie/664767']");
    private final By movieSort = By.cssSelector("#page_1 div.content a[href='/movie/160']");
    private final By searchButtonBy = By.cssSelector("#media_v4 div.content div.apply.small.background_color.light_blue.enabled > p");
    private final By cardsBy = By.cssSelector("#page_1>div>div.image");
    private final By sortBy = By.cssSelector("#media_v4 div.content div.filter span.k-select > span");
    private final By dateAscBy = By.cssSelector("#sort_by_listbox > li[data-offset-index='5']");
    private final By moviesListBy = By.cssSelector("section#media_results div#page_1>div>div.content>p");

    public CatalogPage(WebDriver driver){
        super(driver);
    }

    @Step("Waits for results.")
    public CatalogPage waitForResults(){
        WebDriverWait waitResults = new WebDriverWait(driver, 5);
        waitResults.until(ExpectedConditions.numberOfElementsToBeMoreThan(searchResultsBy,1));
        return new CatalogPage(driver);
    }

    @Step("Reads movie title.")
    public String readMovieTitle(){
        return driver.findElements(searchResultsBy).get(0).getText();
    }

    @Step("Clicks filter button.")
    public CatalogPage clickFiltersButton(){
        driver.findElements(filtersButtonBy).get(1).click();
        return new CatalogPage(driver);
    }

    @Step("Clicks action button.")
    public CatalogPage clickActionButton(){
        driver.findElement(actionButtonBy).click();
        return new CatalogPage(driver);
    }

    @Step("Clicks search button.")
    public CatalogPage clickSearchButton(){
        driver.findElement(searchButtonBy).click();
        return new CatalogPage(driver);
    }

    @Step("Waits for refreshing results.")
    public CatalogPage waitForRefreshFilter(){
        WebDriverWait waitAsc = new WebDriverWait(driver,2);
        waitAsc.until(ExpectedConditions.presenceOfElementLocated(movieFilter));
        return new CatalogPage(driver);
    }

    @Step("Waits for refresh after sorting.")
    public CatalogPage waitForRefreshSort(){
        WebDriverWait waitAsc = new WebDriverWait(driver,1);
        waitAsc.until(ExpectedConditions.presenceOfElementLocated(movieSort));
        return new CatalogPage(driver);
    }

    @Step("Waits for movie cards.")
    public CatalogPage waitForMovieCards(){
        WebDriverWait wait2 = new WebDriverWait(driver, 5);
        wait2.until(ExpectedConditions.numberOfElementsToBeMoreThan(cardsBy,10));
        return new CatalogPage(driver);
    }

    @Step("Clicks a random movie.")
    public MoviePage clickRandomMovie(){
        Random rand = new Random();
        List<WebElement> cards = driver.findElements(cardsBy);
        cards.get(rand.nextInt(0,cards.size()-1)).click();
        return new MoviePage(driver);
    }

    @Step("Clicks the Results by dropdown.")
    public CatalogPage clickSortResultsBy(){
        driver.findElement(sortBy).click();
        return new CatalogPage(driver);
    }

    @Step("Clicks ascending release date.")
    public CatalogPage clickAscendingOrder(){
        driver.findElement(dateAscBy).click();
        return new CatalogPage(driver);
    }

    @Step("Checks ascending order of the 4 first movies.")
    public Boolean checkAscendingOrder(){
        List<WebElement> moviesList = driver.findElements(moviesListBy);
        for (int i = 0; i < 4 ; i++)
            if (Integer.parseInt(moviesList.get(i + 1).getText().split(", ")[1]) <
                    Integer.parseInt(moviesList.get(i).getText().split(", ")[1]))
                return false;
        return true;
    }



}
