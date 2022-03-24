package challenge.entities;

import challenge.tests.Tests;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;
import javax.imageio.ImageIO;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Hooks {

    protected WebDriver driver;
    protected String url = "https://www.themoviedb.org/";
    public static final Logger logger = LogManager.getLogger(Tests.class);


    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        logger.info("Tests are starting.");
        driver.manage().window().maximize();
        driver.navigate().to(url);
    }

    @AfterMethod
    public void tearDown(ITestResult testResult) {
        if (testResult.getStatus() == ITestResult.FAILURE) {

            Date date = new Date();

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");

            Screenshot screenshot = new AShot()
                    .shootingStrategy(ShootingStrategies.viewportPasting(1000))
                    .takeScreenshot(driver);

            String screenshotPath = "C:/Users/jusaenz/Documents/selenium-challenge/selenium-training/failed-tests-screenshots/"
                    + testResult.getName() + "-" + simpleDateFormat.format(date) + ".png";

            try {
                ImageIO.write(screenshot.getImage(), "PNG", new File(screenshotPath));
                logger.info("A screenshot was taken for "+ testResult.getName() + " failed test.");
            }
            catch (Exception e) {
                e.printStackTrace();
                logger.error("The screenshot could not be taken. Error: " + e);
            }
        }

        logger.info("Tests are ending.");
        driver.quit();
    }
}
