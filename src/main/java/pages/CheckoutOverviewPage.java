package pages;

import core.driver.AppDriver;
import core.util.ActionUtil;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutOverviewPage {

    private AppiumDriver driver;

    public CheckoutOverviewPage() {
        driver = AppDriver.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-FINISH\"]")
    private WebElement btnFinish;

    public void scrollToBottom(String msg) {
        ActionUtil.scrollToContentDesc("test-FINISH",msg);
    }

    public void clickFinishBtn(String msg) {
        ActionUtil.click(btnFinish, msg);
    }
}
