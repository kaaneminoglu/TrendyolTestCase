package stepDefinitions;

import base.BaseMethods;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.support.PageFactory;
import pageObjects.LoginElements;

public class LoginSteps extends BaseMethods {
    private final LoginElements login;

    public LoginSteps() {
        login = PageFactory.initElements(BaseSteps.getDriver(), LoginElements.class);
    }

    @When("{string} and {string} are entered.")
    public void setMailAndPassword(String userName, String password) {
        waitDisplayingWebElement(login.txt_email);
        clearText(login.txt_email);
        sendText(login.txt_email, userName);
        clearText(login.txt_password);
        sendText(login.txt_password, password);
        clickWebElement(login.btn_submit);
    }

    @When("Error message is {string}")
    public void checkErrorMessage(String errorMessage) {
        if (!errorMessage.equals(login.lbl_error_message.getText())) {
            takeScreenshot();
            Assert.fail("Failed to error message");
        }
    }
}