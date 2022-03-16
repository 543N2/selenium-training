package challengePOM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class Hooks {

    protected WebDriver driver;
    protected String url = "https://www.themoviedb.org/";


    @BeforeMethod
    public void setup(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to(url);
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
