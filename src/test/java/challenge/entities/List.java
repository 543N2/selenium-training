package challenge.entities;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public abstract class List {

    protected String id;
    protected String name;
    protected String description;
    protected String language;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getLanguage() {
        return language;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Response addMovieToListRequest(Endpoint endpoint, Session session, Movie movie)
    {
        Map<String,Object> bodyMovieList = new HashMap<>();
        bodyMovieList.put("media_id", movie.getId());
        return (
                given().contentType(ContentType.JSON)
                        .body(bodyMovieList)
                        .queryParam("api_key", endpoint.getApiKey())
                        .and().queryParam("session_id",session.getSessionId())
                        .when()
                        .post(endpoint.getAddMovieToList(this.getId()))
                        .then()
                        .extract()
                        .response()
        );
    };

    public abstract Response createRequest(Endpoint endpoint, Session session);

    }
