package challenge.entities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static challenge.entities.Hooks.logger;

public class User {
    private String username;
    private String password;
    private String fakeUsername;
    private String fakePassword;
    private String apiKey;

    public User(){
        this.readProperties();
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public  String getFakeUsername() {
        return fakeUsername;
    }

    public void setFakeUsername(String fakeUsername) {
        this.fakeUsername = fakeUsername;
    }

    public String getFakePassword() {
        return fakePassword;
    }

    public void setFakePassword(String fakePassword) {
        this.fakePassword = fakePassword;
    }

    public String getApiKey() { return apiKey; }

    public void setApiKey(String apiKey) { this.apiKey = apiKey; }

    public void readProperties(){
        Properties properties = new Properties();
        String path = "src/test/resources/config.properties";
        try {
            FileInputStream fileInputStream = new FileInputStream(path);
            properties.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("The properties file could not be read.");
        }

        setUsername(properties.getProperty("USERNAME"));
        setPassword(properties.getProperty("PASSWORD"));
        setFakeUsername(properties.getProperty("FAKE_USERNAME"));
        setFakePassword(properties.getProperty("FAKE_PASSWORD"));
        setApiKey(properties.getProperty("API_KEY"));
    }
}
