package Selenium.Windows;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Set;

public class WindowsGooglePractice {

    WebDriver driver;

    @BeforeMethod
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

    }


    @Test
    public void test1() throws InterruptedException {
        driver.get("https://www.google.com/");
        String currentWindowID = driver.getWindowHandle();
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys("Programming"+ Keys.ENTER);
        System.out.println("the title of the main window: "+ driver.getTitle());

        WebElement wikiPage = driver.findElement(By.xpath("//span[.='Programming language - Wikipedia']"));
        wikiPage.click();

        Set<String> windows = driver.getWindowHandles();
        String newWindow = "";

        for (String id: windows) {
            if (!id.equals(currentWindowID)){
                newWindow = id;
            }

        }

        driver.switchTo().window(newWindow);
        System.out.println("The title of the child window : "+ driver.getTitle());

    }
}
