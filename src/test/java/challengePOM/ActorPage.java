package challengePOM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;
import java.util.Random;

public class ActorPage extends BasePage {

    private final By actingTimelineBy = By.cssSelector("div.credits_list table a.tooltip>bdi");

    public ActorPage(WebDriver driver) {
        super(driver);
    }

    public Boolean checkTimeline(String movieTitle){
        List<WebElement> actingTimeline = driver.findElements(actingTimelineBy);
        for(WebElement element : actingTimeline)
            if (element.getText().equals(movieTitle)) return true;
        return false;
    }
}
