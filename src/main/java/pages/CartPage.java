package pages;

import core.driver.AppDriver;
import core.util.ActionUtil;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage {

    private AppiumDriver driver;

    public CartPage() {
        this.driver = AppDriver.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-CHECKOUT\"]")
    private WebElement btnCheckout;

    public void clickCheckoutButton(String msg) {
        ActionUtil.click(btnCheckout, msg);
    }
}
