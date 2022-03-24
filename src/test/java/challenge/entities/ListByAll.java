package challenge.entities;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class ListByAll extends List {

    public ListByAll(String name, String description, String language){
        this.name = name;
        this.description = description;
        this.language = language;
    }

    public Response createRequest(Endpoint endpoint, Session session){
        Map<String,Object> bodyList = new HashMap<>();
        bodyList.put("name", this.getName());
        bodyList.put("description", this.getDescription());
        bodyList.put("language", this.getLanguage());
        Response response =
                given()
                        .contentType(ContentType.JSON)
                        .body(bodyList)
                        .queryParam("api_key", endpoint.getApiKey())
                        .and().queryParam("session_id",session.getSessionId())
                .when()
                        .post(endpoint.getListCreate())
                .then()
                        .extract()
                        .response();
        System.out.println(response.prettyPrint());
        this.setId(response.path("list_id").toString());
        return response;
    }

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

}
