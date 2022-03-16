package challenge;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Runner {

    String username = "543n2";
    String password = "543n2Movies";
    String url = "https://www.themoviedb.org/";

    @Test
    public void successfulLogin(){
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to(url);

        By loginMainBy = By.cssSelector("a[href='/login']");
        driver.findElement(loginMainBy).click();

        By usernameInputBy = By.id("username");
        driver.findElement(usernameInputBy).sendKeys(username);

        By passwordInputBy = By.id("password");
        driver.findElement(passwordInputBy).sendKeys(password);

        By loginButtonBy = By.id("login_button");
        driver.findElement(loginButtonBy).click();

        By usernameDisplayBy = By.cssSelector("div[class='about'] a[href='/u/"+ username +"']");
        String usernameDisplay = driver.findElement(usernameDisplayBy).getText();
        Assert.assertEquals(username,usernameDisplay);

        driver.quit();
    }

    @Test
    public void failedLogin(){
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to(url);

        By loginMainBy = By.cssSelector("a[href='/login']");
        driver.findElement(loginMainBy).click();

        By usernameInputBy = By.id("username");
        driver.findElement(usernameInputBy).sendKeys("asdfasdf@gmail.com");

        By passwordInputBy = By.id("password");
        driver.findElement(passwordInputBy).sendKeys("asdfasd1321554");

        By loginButtonBy = By.id("login_button");
        driver.findElement(loginButtonBy).click();

        By redErrorBy = By.cssSelector("div[class='carton']>a>h2");
        String backgroundColor = driver.findElement(redErrorBy).getCssValue("background-color");
        Assert.assertEquals(backgroundColor,"rgba(212, 2, 66, 1)");

        By redErrorMessageBy = By.cssSelector("div[class='carton']>a>h2>span");
        String redErrorMessage = driver.findElement(redErrorMessageBy).getText().replace("&nbsp;","");
        Assert.assertEquals(" There was a problem",redErrorMessage);

        By twoErrorMessagesBy = By.cssSelector("div.carton>div.content>ul>li");
        int numberOfMessages = driver.findElements(twoErrorMessagesBy).size();
        Boolean areTwoMessages = false;
        if(numberOfMessages == 2)
            areTwoMessages = true;
        Assert.assertEquals(areTwoMessages,true);

        driver.quit();
    }

    @Test
    public void successfulSearch(){
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to(url);

        By searchButtonBy = By.cssSelector("header a.search>span");
        driver.findElement(searchButtonBy).click();

        By searchBarBy = By.cssSelector("div.search_bar form#search_form input#search_v4");
        driver.findElement(searchBarBy).sendKeys("Fight Club"+ Keys.RETURN);

        By searchResultsBy = By.cssSelector("section.panel div.search_results.movie>div.results.flex div.wrapper div.title h2");
        WebDriverWait waitResults = new WebDriverWait(driver, 5);
        waitResults.until(ExpectedConditions.numberOfElementsToBeMoreThan(searchResultsBy,1));
        String movieTitle = driver.findElements(searchResultsBy).get(0).getText();
        Assert.assertEquals(movieTitle,"Fight Club");

        driver.quit();
    }

    @Test
    public void verifyMovieGenreFilter() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to(url);

        By moviesLinkBy = By.cssSelector("header div.sub_media ul.navigation>li>a[href='/movie']");
        WebElement moviesLink = driver.findElement(moviesLinkBy);
        Actions action = new Actions(driver);
        action.moveToElement(moviesLink).build().perform();

        By topRatedButtonBy = By.cssSelector("a[href='/movie/top-rated']");
        driver.findElement(topRatedButtonBy).click();

        By filtersButtonBy = By.cssSelector("div.filter_panel>div.name");
        driver.findElements(filtersButtonBy).get(1).click();

        By actionButtonBy = By.cssSelector("#with_genres>li>a[href*='with_genres=28']");
        driver.findElement(actionButtonBy).click();
        driver.findElements(filtersButtonBy).get(1).click();

        By searchButtonBy = By.cssSelector("#media_v4 > div > div > div.content > div:nth-child(1) > div.apply.small.background_color.light_blue.enabled > p");
        driver.findElement(searchButtonBy).click();

        //TODO change it please
        //Thread.sleep(1000);
        By movieAsc = By.cssSelector("#page_1 div.content h2>a[href='/movie/664767']");
        WebDriverWait waitAsc = new WebDriverWait(driver,2);
        waitAsc.until(ExpectedConditions.presenceOfElementLocated(movieAsc));

        By cardsBy = By.cssSelector("#page_1>div>div.image");
        WebDriverWait wait2 = new WebDriverWait(driver, 5);
        wait2.until(ExpectedConditions.numberOfElementsToBeMoreThan(cardsBy,10));

        Random rand = new Random();
        List<WebElement> cards = driver.findElements(cardsBy);
        cards.get(rand.nextInt(0,cards.size()-1)).click();

        By genresBy = By.cssSelector("section#original_header span.genres>a");
        List<WebElement> genres = driver.findElements(genresBy);
        Boolean isAction = false;
        for (WebElement g : genres){
            if(g.getText().equals("Action")){
                isAction = true;
                break;
            }
        }
        Assert.assertEquals(isAction,true);

        driver.quit();
    }

    @Test
    public void validateActingTimeline(){
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to("https://www.themoviedb.org/");

        By moviesLinkBy = By.cssSelector("header div.sub_media ul.navigation>li>a[href='/movie']");
        WebElement moviesLink = driver.findElement(moviesLinkBy);
        Actions action = new Actions(driver);
        action.moveToElement(moviesLink).build().perform();

        By nowPlayingButtonBy = By.cssSelector("a[href='/movie/now-playing']");
        driver.findElement(nowPlayingButtonBy).click();

        By cardsBy = By.cssSelector("#page_1>div>div.image");
        Random rand = new Random();
        List<WebElement> cards = driver.findElements(cardsBy);
        cards.get(rand.nextInt(0,cards.size()-1)).click();

        driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);

        By movieTitleBy = By.cssSelector("div.header_poster_wrapper section > div > h2 > a");
        String movieTitle = driver.findElement(movieTitleBy).getText();

        By castBy = By.cssSelector("#cast_scroller>ol>li");
        Random rand2 = new Random();
        List<WebElement> cast = driver.findElements(castBy);
        cast.get( rand2.nextInt(0,cast.size()) ).click();

        By actingTimelineBy = By.cssSelector("div.credits_list table a.tooltip>bdi");
        List<WebElement> actingTimeline = driver.findElements(actingTimelineBy);
        Boolean movieInTimeline = false;
        for(WebElement element : actingTimeline){
            if(element.getText().equals(movieTitle)){
                movieInTimeline = true;
                break;
            }
        }
        Assert.assertEquals(movieInTimeline, true);
         //TODO: check the movie has actors
        driver.quit();
    }

    @Test
    public void sortByDatesOnAscendingOrder() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to(url);

        By moviesLinkBy = By.cssSelector("header div.sub_media ul.navigation>li>a[href='/movie']");
        WebElement moviesLink = driver.findElement(moviesLinkBy);
        Actions action = new Actions(driver);
        action.moveToElement(moviesLink).build().perform();

        By topRatedButtonBy = By.cssSelector("a[href='/movie/top-rated']");
        driver.findElement(topRatedButtonBy).click();

        //By sortBy = By.cssSelector("#media_v4 > div > div > div.content > div:nth-child(1) > div:nth-child(1) > div.filter > span > span > span.k-select > span");
        By sortBy = By.cssSelector("#media_v4 div.content div.filter span.k-select > span");
        driver.findElement(sortBy).click();

        By dateAscBy = By.cssSelector("#sort_by_listbox > li[data-offset-index='5']");
        driver.findElement(dateAscBy).click();

        //By searchButtonBy = By.cssSelector("#media_v4 > div > div > div.content > div:nth-child(1) > div.apply.small.background_color.light_blue.enabled > p");
        By searchButtonBy = By.cssSelector("#media_v4 div.content div.apply.small.background_color.light_blue.enabled > p");
        driver.findElement(searchButtonBy).click();

        //TODO change it please
        //Thread.sleep(1000);
        By movieAsc = By.cssSelector("#page_1 div.content a[href='/movie/160']");
        WebDriverWait waitAsc = new WebDriverWait(driver,1);
        waitAsc.until(ExpectedConditions.presenceOfElementLocated(movieAsc));

        By moviesListBy = By.cssSelector("section#media_results div#page_1>div>div.content>p");
        List<WebElement> moviesList = driver.findElements(moviesListBy);
        Boolean inAscendingOrder = false;
        for (int i = 0; i < 4 ; i++) {
            System.out.println(moviesList.get(i).getText());
            if (    i>0 &&
                    i<4 &&
                    Integer.parseInt(moviesList.get(i+1).getText().split(", ")[1]) >
                            Integer.parseInt(moviesList.get(i).getText().split(", ")[1])
            )
            {
                inAscendingOrder = true;
            }
        }
        Assert.assertEquals(inAscendingOrder,true);
        driver.quit();
    }

}
