package challenge;

import challenge.pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Runner extends Hooks {


    @Test
    public void successfulLogin(){
        logger.info("sucessfulLogin Test started.");
        LandingPage landing = new LandingPage(driver);
        LoginPage login = landing
                .clickLoginButton();
        UserPage user = login
                .typeUsername()
                .typePassword()
                .clickLoginButton();
        Assert.assertEquals( login.username(), user.readUsername());
        logger.info("sucessfulLogin Test ended.");
    }

    @Test
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

    @Test
    public void successfulSearch(){
        logger.info("sucessfulSearch Test started.");

        String expectedMovie = "Fight Club";

        LandingPage landing = new LandingPage(driver);

        CatalogPage catalog = landing
                .clickSearchIconButton()
                .typeMovieTitleAndSend(expectedMovie);

        String movieTitle = catalog
                .waitForResults()
                .readMovieTitle();

        Assert.assertEquals(movieTitle,expectedMovie);

        logger.info("sucessfulSearch Test ended.");

    }

    @Test
    public void verifyMovieGenreFilter() throws InterruptedException {
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


    @Test
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

    @Test
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
