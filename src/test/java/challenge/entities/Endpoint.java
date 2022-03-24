package challenge.entities;

public class Endpoint {

    public String getUrl() { return "https://api.themoviedb.org/3"; }

    public String getApiKey() {
        return "0383f0931f7f6bb14fe64530a706ce6c";
    }

    public String getToken() {
        return "/authentication/token/new";
    }

    public String getLogin(){
        return "/authentication/token/validate_with_login";
    }

    public String getSession(){
        return "/authentication/session/new";
    }

    public String getListCreate(){
        return "/list";
    }

    public String getAddMovieToList(String listId){ return "/list/" + listId + "/add_item"; }

}
