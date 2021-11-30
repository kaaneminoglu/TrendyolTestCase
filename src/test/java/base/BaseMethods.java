package base;

import com.opencsv.CSVWriter;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.commons.io.FileUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import stepDefinitions.BaseSteps;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class BaseMethods {
    protected static String folderName;
    private final WebDriver driver = BaseSteps.getDriver();

    protected void clickWebElement(WebElement element) {
        try {
            WebDriverWait wait =
                    new WebDriverWait(driver, 3, 500);
            wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        } catch (Exception e) {
            takeScreenshot();
            e.printStackTrace();
        }
    }

    protected void sendText(WebElement element, String text) {
        try {
            WebDriverWait wait =
                    new WebDriverWait(driver, 3, 500);
            wait.until(ExpectedConditions.elementToBeClickable(element)).sendKeys(text);
        } catch (Exception e) {
            takeScreenshot();
            e.printStackTrace();
        }
    }

    protected void clearText(WebElement element) {
        try {
            WebDriverWait wait =
                    new WebDriverWait(driver, 3, 500);
            wait.until(ExpectedConditions.elementToBeClickable(element)).clear();
        } catch (Exception e) {
            takeScreenshot();
            e.printStackTrace();
        }
    }

    public void waitBySeconds(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void waitDisplayingWebElement(WebElement element) {
        try {
            WebDriverWait wait =
                    new WebDriverWait(driver, 3, 500);
            wait.until(ExpectedConditions.visibilityOf(element));
        } catch (Exception e) {
            takeScreenshot();
            e.printStackTrace();
        }
    }

    public Integer getResponseCodeWithUrl(String url) {
        Response response = RestAssured.get(url);
        return response.getStatusCode();
    }

    protected void scrollToBottom() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
    }

    protected String getDurationWithName(String imageLink) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return js.executeScript("return window.performance.getEntries().filter(e=>e.name==='" + imageLink + "').map(item=>item.duration);").toString();
    }

    protected String getStatusCodeWithName(String imageLink) throws IOException {
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(imageLink);
        HttpResponse response = client.execute(request);
        return String.valueOf(response.getStatusLine().getStatusCode());
    }

    protected void scrollToWebElement(WebElement webElement) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", webElement);
    }

    protected void takeScreenshot() {
        try {
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String _path =
                    "exportFile/screenshot/"
                            + getFolderName()
                            + "-"
                            + get_currentTime()
                            + "-"
                            + get_randomNumber()
                            + ".png";
            FileUtils.copyFile(scrFile, new File(_path));
        } catch (IOException e) {
            System.out.println("ScreenShot fail." + e.getMessage());
            e.printStackTrace();
        }
    }

    protected void writeCsvFile(List<String[]> list, String path) {
        try {
            String _path = "exportFile/" + path;
            String _path2 = _path + "/" + getFolderName() + "-" + get_currentTime() + "-" + get_randomNumber() + ".csv";
            Boolean result = new File(_path).mkdirs();
            System.out.println(result);
            CSVWriter writer = new CSVWriter(new FileWriter(_path2), ';', CSVWriter.NO_QUOTE_CHARACTER,
                    CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                    CSVWriter.DEFAULT_LINE_END);
            writer.writeAll(list);
            writer.close();
        } catch (IOException e) {
            System.out.println("ScreenShot fail." + e.getMessage());
            takeScreenshot();
            e.printStackTrace();
        }
    }

    public String getFolderName() {
        return folderName;
    }

    public String get_currentTime() {
        String pattern = "yyyy-MM-dd-hh-mm-ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(new Date());
    }

    public String get_randomNumber() {
        return UUID.randomUUID().toString();
    }
}
