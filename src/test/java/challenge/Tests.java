package challenge;

import challenge.pages.*;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

//@Listeners({TestListener.class})
public class Tests extends Hooks {


    @Test(priority=0, description = "Successful login with username and password")
    @Description("Test description: Login test with valid username and password.")
    public void successfulLogin(){
        logger.info("successfulLogin Test started.");
        LandingPage landing = new LandingPage(driver);
        LoginPage login = landing
                .clickLoginButton();
        UserPage user = login
                .typeUsername()
                .typePassword()
                .clickLoginButton();
        Assert.assertEquals( login.username(), user.readUsername());
        logger.info("successfulLogin Test ended.");
    }

    @Test(priority=0, description = "Failed login with both fake username and password")
    @Description("Test description: Login test with invalid username and password.")
    public void failedLogin(){
        logger.info("failedLogin Test started.");

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

        logger.info("failedLogin Test ended.");

    }

    @Test(priority=0, description = "Successful search of a movie")
    @Description("Test description: Search test of a given movie title using the search bar.")
    public void successfulSearch(){
        logger.info("successfulSearch Test started.");

        String expectedMovie = "Fight Club";

        LandingPage landing = new LandingPage(driver);

        CatalogPage catalog = landing
                .clickSearchIconButton()
                .typeMovieTitleAndSend(expectedMovie);

        String movieTitle = catalog
                .waitForResults()
                .readMovieTitle();

        Assert.assertEquals(movieTitle,expectedMovie);

        logger.info("successfulSearch Test ended.");

    }

    @Test(priority=0, description = "Filter test by movie genre")
    @Description("Test description: Movie filter test given the movie genre.")
    public void verifyMovieGenreFilter() {
        logger.info("verifyMovieGenreFilter Test started.");

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

        logger.info("verifyMovieGenreFilter Test ended.");

    }


    @Test(priority=0, description = "Acting timeline validation")
    @Description("Test description: Validates a chosen movie is in the actor's timeline")
    public void validateActingTimeline(){
        logger.info("validateActingTimeline Test started.");

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

        logger.info("validateActingTimeline Test ended.");

    }

    @Test(priority=0, description = " Sorting test by ascending movie date")
    @Description("Test description: Validates movies are shown in ascending order of release date.")
    public void sortByDatesOnAscendingOrder(){
        logger.info("sortByDatesOnAscendingOrder Test started.");

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

        logger.info("sortByDatesOnAscendingOrder Test ended.");

    }

}
