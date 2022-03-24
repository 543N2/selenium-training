package challenge.entities;

import io.restassured.http.ContentType;

import java.util.HashMap;
import java.util.Map;

import static challenge.entities.Hooks.logger;
import static io.restassured.RestAssured.given;

public class Session {

    private String token;
    private String sessionId;


    public void requestToken(Endpoint endpoint, User user){
        setToken(
                given().
                        queryParam("api_key", user.getApiKey())
                .when()
                        .get(endpoint.getToken())
                .then()
                        .extract()
                        .response()
                        .getBody()
                        .path("request_token")
                        .toString()
        );
        logger.info("Token requested. Obtained: " + this.getToken());
    }

    public void loginWithUserAndPassword(Endpoint endpoint, User user){

        Map<String,Object> bodyLogin = new HashMap<>();
        bodyLogin.put("username", user.getUsername());
        bodyLogin.put("password", user.getPassword());
        bodyLogin.put("request_token", getToken());

        given().
                contentType(ContentType.JSON)
                .body(bodyLogin)
        .when()
                .queryParam("api_key", user.getApiKey())
                .post(endpoint.getLogin())
        .then()
                .statusCode(200);
        logger.info("Sent request to login with username ("+ user.getUsername() +"), password ("+ user.getPassword()+").");
    }

    public void requestSessionId(Endpoint endpoint, User user){

        Map<String,Object> bodySession = new HashMap<>();
        bodySession.put("request_token", getToken());

        setSessionId(
                given()
                        .contentType(ContentType.JSON)
                        .body(bodySession)
                .when()
                        .queryParam("api_key", user.getApiKey())
                        .post(endpoint.getSession())
                .then()
                        .extract()
                        .response()
                        .getBody()
                        .path("session_id")
                        .toString()
        );
        logger.info("Session Id requested. Obtained: " + this.getSessionId());
    }

    public String getToken(){
        return token;
    }

    public void setToken(String token){
        this.token = token;
    }

    public String getSessionId(){
        return sessionId;
    }

    public void setSessionId(String sessionId){
        this.sessionId = sessionId;
    }
}
