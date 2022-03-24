package challenge.tests;

import challenge.entities.*;
import challenge.pages.*;
import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Tests extends Hooks {

    @Test(priority=0, description = "Successful login with username and password")
    @Description("Test description: Login test with valid username and password.")
    public void successfulLogin(){
        logger.info("successfulLogin Test started.");

        User user = new User();

        LandingPage landingPage = new LandingPage(driver);
        LoginPage loginPage = landingPage
                .clickLoginButton();
        UserPage userPage = loginPage
                .typeUsername(user)
                .typePassword(user)
                .clickLoginButton();
        Assert.assertEquals( user.getUsername(), userPage.readUsername(user));
        logger.info("successfulLogin Test ended.");
    }

    @Test(priority=0, description = "Failed login with both fake username and password")
    @Description("Test description: Login test with invalid username and password.")
    public void failedLogin(){
        logger.info("failedLogin Test started.");

        User user = new User();
        user.readProperties();

        LandingPage landingPage = new LandingPage(driver);

        LoginPage loginPage = landingPage.clickLoginButton();

        String backgroundColor = loginPage
                .typeFakeUsername(user)
                .typeFakePassword(user)
                .clickLoginButtonToFail()
                .readBoxColor();
        Assert.assertEquals(backgroundColor, loginPage.redColor());

        String redErrorMessage = loginPage.readErrorMessage();
        Assert.assertEquals(redErrorMessage," There was a problem");

        Boolean areTwoErrorMessages = loginPage.numberOfMessages();
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

    @Test(priority = 0, description = "Add movie to list using API and UI")
    @Description("Test description: Creates a list and adds a movie using API. Asserts movie title using UI.")
    public void addMovieToListAPIAndUI(){
        logger.info("addMovieToListAPIAndUI Test started.");

        String movieId = "78";
        String movieName = "Blade Runner";
        String listName = "Challenge List 01";
        String listDescription = "Challenge Description 01";
        String listLanguage = "en";

        User user = new User();
        Endpoint endpoint = new Endpoint();
        Session session = new Session();
        List list = new ListByAll(listName,listDescription, listLanguage);
        Movie movie = new MovieById(movieId);

        RestAssured.baseURI = endpoint.getUrl();

        // Login via API
        session.requestToken(endpoint, user);
        session.loginWithUserAndPassword(endpoint, user);
        session.requestSessionId(endpoint, user);
        Assert.assertNotNull(session.getSessionId());

        // List creation via API
        String listId = list.getId();
        Response responseList = list.createRequest(endpoint, session);
        Assert.assertEquals(201,responseList.getStatusCode());
        logger.info("A List was created. List id: " + list.getId());

        // Movie addition to List via API
        Response responseMovie = list.addMovieToListRequest(endpoint, session, movie);
        Assert.assertEquals(201,responseMovie.getStatusCode());
        logger.info("The movie " + movieName +" was added to the List with id: " + movie.getId());

        // Login via UI
        LandingPage landingPage = new LandingPage(driver);

        LoginPage loginPage = landingPage.clickLoginButton();

        UserPage userPage = loginPage
                .typeUsername(user)
                .typePassword(user)
                .clickLoginButton();
        Assert.assertEquals( user.getUsername(), userPage.readUsername(user));
        logger.info("Successful login Test ended.");

        // Find Movie title and compare
        ListPage listPage = userPage.clickUsernameButton()
                        .clickListOptionDropdown(user)
                        .clickListButton(list);

        MoviePage moviePage = listPage.clickMovieButton(movie);

        String movieTitle = moviePage.readMovieTitleToCompare();

        Assert.assertEquals(movieName,movieTitle);

        logger.info("addMovieToListAPIAndUI Test ended.");
    }

    @Test(priority=0, description = "Intended to fail: UN-Successful login with username and password")
    @Description("Test description: Intended to fail: Login test with valid username and password.")
    public void intentionallyFailedLogin(){
        logger.info("intentionalFailedLogin Test started.");

        User user = new User();

        LandingPage landingPage = new LandingPage(driver);
        LoginPage loginPage = landingPage
                .clickLoginButton();
        UserPage userPage = loginPage
                .typeUsername(user)
                .typePassword(user)
                .clickLoginButton();
        Assert.assertEquals( user.getUsername(), userPage.readUsername(user) + "FAIL!!!");
        logger.info("intentionalFailedLogin Test ended.");
    }

}
