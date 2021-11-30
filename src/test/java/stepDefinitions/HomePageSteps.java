package stepDefinitions;

import base.BaseMethods;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import pageObjects.HomePageElements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HomePageSteps extends BaseMethods {

    private final HomePageElements homePage;

    public HomePageSteps() {
        homePage = PageFactory.initElements(BaseSteps.getDriver(), HomePageElements.class);
    }

    @When("Open login page")
    public void openLoginPage() {
        waitDisplayingWebElement(homePage.btn_login);
        clickWebElement(homePage.btn_login);
    }

    @When("Check account user {string}")
    public void checkAccountUser(String accountUser) {
        waitBySeconds(2);
        waitDisplayingWebElement(homePage.btn_login);
        if (!accountUser.equals(homePage.btn_login.getText())) {
            takeScreenshot();
            Assert.fail("Failed to login");
        }
    }

    @When("Check boutique link")
    public void checkBoutiqueLink() {
        List<WebElement> lstBoutique = homePage.lst_boutique;
        List<String[]> lstResponse = new ArrayList<String[]>();
        lstResponse.add(new String[]{"Boutique Link", "Status Code"});
        for (WebElement webElement : lstBoutique) {
            String boutiqueLink = webElement.getAttribute("href");
            Integer response = getResponseCodeWithUrl(boutiqueLink);
            lstResponse.add(new String[]{boutiqueLink, response.toString()});
            System.out.println(response);
        }
        writeCsvFile(lstResponse, "checkBoutiqueLink");
    }

    @When("Check boutique image duration and status code")
    public void checkBoutiqueImageDurationAndStatusCode() throws IOException {
        List<String[]> lstResponse = new ArrayList<String[]>();
        lstResponse.add(new String[]{"Boutique Link", "Status Code", "Duration"});
        int size = homePage.lst_boutique.size();
        scrollToWebElement(homePage.lst_boutique.get(homePage.lst_boutique.size() - 1));
        waitBySeconds(10);
        int lastSize = homePage.lst_boutique.size();
        for (int i = size; i < lastSize; i++) {
            String imgUrl = homePage.lst_boutique.get(i).findElement(By.xpath("./span/img")).getAttribute("src");
            String statusCode = getStatusCodeWithName(imgUrl);
            String durationTime = getDurationWithName(imgUrl);
            lstResponse.add(new String[]{imgUrl, statusCode, durationTime});
        }
        writeCsvFile(lstResponse, "checkBoutiqueImageDurationAndStatusCode");
    }

    @When("Scroll down to footer")
    public void scrollDownToFooter() {
        scrollToWebElement(homePage.lbl_popular_brands);
    }
}