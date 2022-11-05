package pages;

import core.driver.AppDriver;
import core.util.ActionUtil;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutInfoPage {

    private AppiumDriver driver;

    public CheckoutInfoPage() {
        this.driver = AppDriver.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//android.widget.EditText[@content-desc=\"test-First Name\"]")
    private WebElement firstName;

    @FindBy(xpath = "//android.widget.EditText[@content-desc=\"test-Last Name\"]")
    private WebElement lastName;

    @FindBy(xpath = "//android.widget.EditText[@content-desc=\"test-Zip/Postal Code\"]")
    private WebElement zip;

    @FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-CONTINUE\"]")
    private WebElement btnContinue;

    @FindBy(xpath = "//android.widget.TextView[@text=\"CHECKOUT: INFORMATION\"]")
    private WebElement titlePage;

    public void inputFirstName(String keys, String msg) {
        ActionUtil.sendKeys(firstName, keys, msg);
    }

    public void inputLastName(String keys, String msg) {
        ActionUtil.sendKeys(lastName, keys, msg);
    }

    public void inputZip(String keys, String msg) {
        ActionUtil.sendKeys(zip, keys, msg);
    }

    public void clickContinueBtn(String msg) {
        ActionUtil.click(btnContinue, msg);
    }

    public WebElement getCheckoutInformationTitle() {
        return titlePage;
    }
}
