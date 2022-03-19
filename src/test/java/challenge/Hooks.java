package challenge;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;


public class Hooks {

    protected WebDriver driver;
    protected String url = "https://www.themoviedb.org/";
    public static final Logger logger = LogManager.getLogger(Tests.class);


    @BeforeMethod
    public void setup(){
        driver = new ChromeDriver();
        logger.info("Tests are starting.");
        driver.manage().window().maximize();
        driver.navigate().to(url);
    }

    @AfterMethod
    public void tearDown(){
        logger.info("Tests are ending.");
        driver.quit();
    }
}
