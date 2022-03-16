package training;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Runner {

    @Test
    public void test() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to("https://www.wikipedia.org/");

        By searchBarBy = By.id("searchInput");

        driver.findElement(searchBarBy).sendKeys("Endava");

        By suggestedResultsBy = By.xpath("//div[@class='suggestions-dropdown']/a");

        // Para crear una espera, jamas usar Thread.sleep
        //Thread.sleep(5000);

        // Espera explicita: para esperas con ordenes muy especificas.
        WebDriverWait wait = new WebDriverWait(driver,5);
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(suggestedResultsBy,1));

        // Espera implicita: espera maximo 5s para que la siguiente instruccion se ejecute. Si no, falla.
        //driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        driver.findElements(suggestedResultsBy).get(0).click();

        By articleTitleBy = By.tagName("h1");
        String articleTitle = driver.findElement(articleTitleBy).getText();

        Assert.assertEquals(articleTitle,"Endava");

        driver.quit();
    }
}
