package stepDefinitions;

import base.BaseMethods;
import io.cucumber.java.*;
import org.apache.commons.io.FileUtils;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import utils.Driver;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class BaseSteps extends BaseMethods {
    public static WebDriver driver;
    public WebDriverWait wait;

    @Before()
    public void setUp(Scenario scenario) {
        BaseMethods.folderName = scenario.getName();
        driver = Driver.get();
        wait = new WebDriverWait(driver, 10);
        driver.manage().window().maximize();
        driver.get("https://www.trendyol.com/");
        wait.until(ExpectedConditions.elementToBeClickable(By.id("Combined-Shape"))).click();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {

            final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            String _path =
                    "exportFile/screenshot/"
                            + getFolderName()
                            + "-"
                            + get_currentTime()
                            + "-"
                            + get_randomNumber()
                            + ".png";
            scenario.attach(screenshot, _path, scenario.getName());
            System.out.println("ScreenShot fail.");
        }
        Driver.closeDriver();
    }

    public static WebDriver getDriver() {
        return driver;
    }
}