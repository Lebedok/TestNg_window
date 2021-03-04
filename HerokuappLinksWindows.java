package Do_enjoy_while_coding;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.LinkedList;

public class HerokuappLinksWindows {
    WebDriver driver;
    Actions actions;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        actions = new Actions(driver);
    }

    @Test
    public void test1() throws InterruptedException {
        driver.get("https://the-internet.herokuapp.com/");
        LinkedList<WebElement> allLinks = new LinkedList<>(driver.findElements(By.xpath("//a")));
        String parentWindow = driver.getWindowHandle();
        int linkNumber = 1;
        do {
            WebElement tempLink = allLinks.get(linkNumber);
            actions.keyDown(Keys.COMMAND).click(tempLink).perform();
            Thread.sleep(2000);
            actions.keyUp(Keys.COMMAND).perform();
            LinkedList<String> windows = new LinkedList<String>(driver.getWindowHandles());
            String tempWindowId = windows.get(windows.size() - 1);
            driver.switchTo().window(tempWindowId);
            System.out.println(linkNumber - 1 + ". title " + driver.getTitle());
            if (driver.getPageSource().contains("h3")) {
                String h3 = driver.findElement(By.tagName("h3")).getText();
                System.out.println(h3);
            }
            linkNumber++;
            driver.close();
            driver.switchTo().window(parentWindow);
        } while (linkNumber < allLinks.size() - 1);
    }
}
