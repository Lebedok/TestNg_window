package Selenium.Windows;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Set;

public class WindowsIntro {
    WebDriver driver;
    Actions actions;


    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        actions = new Actions(driver);
    }

    @Test
    public void test() throws InterruptedException {
        driver.get("https://google.com");
        String currentWindowID = driver.getWindowHandle();
        WebElement searchBox = driver.findElement(By.name("q"));
        actions.sendKeys(searchBox, "java programming" + Keys.ENTER).perform();

        WebElement _3rdLink = driver.findElement(By.xpath("//span[text()='Learn Java Programming - Programiz']"));
        actions.keyDown(Keys.SHIFT).click(_3rdLink).keyUp(Keys.SHIFT).perform();

        Set<String> allWindowIDs = driver.getWindowHandles();
        String newWindowID = "";
        for (String id : allWindowIDs) {
            if (!id.equals(currentWindowID)) {
                newWindowID = id;
            }
        }

        driver.switchTo().window(newWindowID);
        // operations on
        WebElement header = driver.findElement(By.tagName("h1"));
        Assert.assertEquals(header.getText(), "Learn Java Programming");

        Thread.sleep(2000);

        driver.switchTo().window(currentWindowID);
        WebElement wikiLink = driver.findElement(By.xpath("//span[.='Java (programming language) - Wikipedia']"));

        // open link in a new window
        actions.keyDown(Keys.SHIFT).click(wikiLink).keyUp(Keys.SHIFT).perform();

        // reassigning
        allWindowIDs = driver.getWindowHandles();
        String thirdWindowId = "";

        for (String id : allWindowIDs) {
            if (!id.equals(currentWindowID) && !id.equals(newWindowID)){
                thirdWindowId = id;

            }
        }

        driver.switchTo().window(thirdWindowId);
        // new windows URL
        System.out.println(driver.getCurrentUrl());
        // new window title
        System.out.println(driver.getTitle());

    }

}

