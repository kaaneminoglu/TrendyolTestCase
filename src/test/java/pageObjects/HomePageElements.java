package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class HomePageElements {

    @FindBy(xpath = "//*[@id='account-navigation-container']/div/div[1]")
    public WebElement btn_login;

    @FindBy(xpath = "//*[@id='browsing-gw-homepage']/div/div[2]/div//article/a")
    public List<WebElement> lst_boutique;

    @FindBy(id = "marketing-internal-linking")
    public WebElement lbl_popular_brands;
}
