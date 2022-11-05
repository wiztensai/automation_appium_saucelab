package pages;

import core.driver.AppDriver;
import core.reporter.ExtentUtil;
import core.util.ActionUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    public LoginPage() {
        PageFactory.initElements(AppDriver.getDriver(), this);
    }

    @FindBy(xpath = "//android.widget.EditText[@content-desc=\"test-Username\"]")
    private WebElement username;

    @FindBy(xpath = "//android.widget.EditText[@content-desc=\"test-Password\"]")
    private WebElement password;

    @FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-LOGIN\"]")
    public WebElement btnLogin;

    @FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Error message\"]/android.widget.TextView")
    private WebElement errorMessage;

    public void login(String username, String password) {
        ActionUtil.sendKeys(this.username, username, "Input "+username+" to username textbox");
        ActionUtil.sendKeys(this.password, password, "Input "+password+" to password textbox");
        ActionUtil.click(btnLogin, "Click login button");
    }

    public String getErrorMessage() {
        return errorMessage.getText();
    }
}
