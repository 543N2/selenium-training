package challengePOM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class Runner extends Hooks {
    //String username = "543n2";
    String password = "543n2Movies";



    @Test
    public void successfulLogin(){
        LandingPage landing = new LandingPage(driver);
        LoginPage login = landing
                .clickLoginButton();
        UserPage user = login
                .typeUsername()
                .typePassword()
                .clickLoginButton();
        Assert.assertEquals( login.username(), user.readUsername());
    }

    @Test
    public void failedLogin(){
        LandingPage landing = new LandingPage(driver);

        LoginPage login = landing.clickLoginButton();

        String backgroundColor = login
                .typeFakeUsername()
                .typeFakePassword()
                .clickLoginButtonToFail()
                .readBoxColor();
        Assert.assertEquals(backgroundColor,login.redColor());

        String redErrorMessage = login.readErrorMessage();
        Assert.assertEquals(redErrorMessage," There was a problem");

        Boolean areTwoErrorMessages = login.numberOfMessages();
        Assert.assertEquals(areTwoErrorMessages,true);
    }

    @Test
    public void successfulSearch(){
        String expectedMovie = "Fight Club";

        LandingPage landing = new LandingPage(driver);

        CatalogPage catalog = landing
                .clickSearchIconButton()
                .typeMovieTitleAndSend(expectedMovie);

        String movieTitle = catalog
                .waitForResults()
                .readMovieTitle();

        Assert.assertEquals(movieTitle,expectedMovie);
    }

    @Test
    public void verifyMovieGenreFilter() throws InterruptedException {
        LandingPage landing = new LandingPage(driver);

        CatalogPage catalog = landing
                .hoverMoviesLink()
                .clickTopRatedOption();

        MoviePage movie = catalog
                .clickFiltersButton()
                .clickActionButton()
                .clickFiltersButton()
                .clickSearchButton()
                .waitForRefreshFilter()
                .waitForMovieCards()
                .clickRandomMovie();

        Boolean isActionMovie = movie
                .isActionMovie();

        Assert.assertEquals(isActionMovie,true);
    }


    @Test
    public void validateActingTimeline(){
        LandingPage landing = new LandingPage(driver);

        CatalogPage catalog = landing
                .hoverMoviesLink()
                .clickNowPlayingOption();

        MoviePage movie = catalog
                .clickRandomMovie();

        String movieTitle = movie
                .waitImplicit()
                .readMovieTitleToCompare();

        ActorPage actor = movie
                .selectAnActor();

        Boolean movieInTimeline = actor.checkTimeline(movieTitle);

        Assert.assertEquals(movieInTimeline, true);
    }

    @Test
    public void sortByDatesOnAscendingOrder() throws InterruptedException {
        LandingPage landing = new LandingPage(driver);

        CatalogPage catalog = landing
                .hoverMoviesLink()
                .clickTopRatedOption();

        Boolean inAscendingOrder = catalog
                .clickSortResultsBy()
                .clickAscendingOrder()
                .clickSearchButton()
                .waitForRefreshSort()
                .checkAscendingOrder();

        Assert.assertEquals(inAscendingOrder,true);
    }

}
