package pages;

import core.driver.AppDriver;
import core.reporter.ExtentUtil;
import core.util.ActionUtil;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductListPage {
    private AppiumDriver driver;

    public ProductListPage() {
        driver = AppDriver.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//android.widget.ScrollView[@content-desc=\"test-PRODUCTS\"]")
    private List<WebElement> products;

    @FindBy(xpath = "(//android.view.ViewGroup[@content-desc=\"test-ADD TO CART\"])[1]")
    private WebElement btnAddToCartFirstItem;

    @FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Cart\"]")
    private WebElement btnCart;

    @FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Modal Selector Button\"]")
    private WebElement btnFilter;

    @FindBy(xpath = "//android.widget.TextView[@text=\"Name (A to Z)\"]")
    private WebElement btnSortNameAZ;

    @FindBy(xpath = "//android.widget.TextView[@text=\"Name (Z to A)\"]")
    private WebElement btnSortNameZA;

    @FindBy(xpath = "//android.widget.TextView[@text=\"Price (low to high)\"]")
    private WebElement btnSortPriceLohi;

    @FindBy(xpath = "//android.widget.TextView[@text=\"Price (high to low)\"]")
    private WebElement btnSortPriceHilo;

    @FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Menu\"]")
    private WebElement btnHamburger;

    public void selectFirstItem(String msg) {
        ActionUtil.click(btnAddToCartFirstItem, msg);
    }

    public void clickCartButton(String msg){
        ActionUtil.click(btnCart, msg);
    }

    public void clickBtnHamburger() {
        ActionUtil.click(btnHamburger, "Tap hamburger button in top navigation");
    }

    public void clickLogoutButton() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        ActionUtil.tapPoint(216, 1440);

        ExtentUtil.logInfo("Tap LOGOUT button");
    }

    public void clickBtnFilter() {
        ActionUtil.click(btnFilter, "Tap filter button in top navigation");
    }

    public void clickBtnSortNameAZ() {
        ActionUtil.click(btnSortNameAZ, "Click 'Name (A to Z)' button");
    }

    public void clickBtnSortNameZA() {
        ActionUtil.click(btnSortNameZA, "Click 'Name (Z to A)' button");
    }

    public void clickBtnSortPriceLohi() {
        ActionUtil.click(btnSortPriceLohi, "Click 'Price (low to high)' button");
    }

    public void clickBtnSortPriceHilo() {
        ActionUtil.click(btnSortPriceHilo, "Click 'Price (high to low)' button");
    }

    public void waitUntilPlpVisible() {
        ActionUtil.waitForVisibility(btnAddToCartFirstItem);
    }
}
