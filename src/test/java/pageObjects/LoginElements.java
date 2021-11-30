package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LoginElements {

    @FindBy(id = "login-email")
    public WebElement txt_email;

    @FindBy(id = "login-password-input")
    public WebElement txt_password;

    @FindBy(xpath = "//*[@id='login-register']/div[3]/div[1]/form/button")
    public WebElement btn_submit;

    @FindBy(id = "error-box-wrapper")
    public WebElement lbl_error_message;
}
