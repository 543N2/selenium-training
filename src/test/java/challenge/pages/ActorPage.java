package challenge.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class ActorPage extends BasePage {

    private final By actingTimelineBy = By.cssSelector("div.credits_list table a.tooltip>bdi");

    public ActorPage(WebDriver driver) {
        super(driver);
    }

    @Step("Checks timeline.")
    public Boolean checkTimeline(String movieTitle){
        List<WebElement> actingTimeline = driver.findElements(actingTimelineBy);
        for(WebElement element : actingTimeline)
            if (element.getText().equals(movieTitle)) return true;
        return false;
    }
}
