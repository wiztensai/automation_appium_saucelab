package tests;

import core.BaseTest;
import core.reporter.ExtentUtil;
import core.util.ActionUtil;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductListPage;

public class SortingProductListPage extends BaseTest {

    LoginPage loginPage;
    ProductListPage productListPage;

    @BeforeClass
    public void beforeTest() {
        ActionUtil.startMainActivity();

        loginPage = new LoginPage();
        productListPage = new ProductListPage();
    }

    @Test
    public void sortNameAZ() {
        loginPage.login("standard_user", "secret_sauce");

        productListPage.waitUntilPlpVisible();
        productListPage.clickBtnFilter();
        productListPage.clickBtnSortNameAZ();

        ExtentUtil.logInfo("Validates that names are in order from A to Z");
        demoScrolldown();
    }

    @Test(dependsOnMethods = "sortNameAZ")
    public void sortNameZA() {
        productListPage.clickBtnFilter();
        productListPage.clickBtnSortNameZA();

        ExtentUtil.logInfo("Validates that names are in order from Z to A");
        demoScrolldown();
    }

    @Test(dependsOnMethods = "sortNameAZ")
    public void sortPriceLohi() {
        productListPage.clickBtnFilter();
        productListPage.clickBtnSortPriceLohi();

        ExtentUtil.logInfo("Validates that names price in order from low to high");
        demoScrolldown();
    }

    @Test(dependsOnMethods = "sortNameAZ")
    public void sortPriceHilo() {
        productListPage.clickBtnFilter();
        productListPage.clickBtnSortPriceHilo();

        ExtentUtil.logInfo("Validates that names price in order from high to low");
        demoScrolldown();
    }

    /**
     * method scroll ini, kayak ngga mau nunggu. jadi langsung ke-eksekusi
     * oleh karena itu, awal method ini diberi thread.sleep
     *
     * jika tidak diberi thread.sleep, maka swipe cuma sekali,
     * padahal sudah ditulis 2x
     */
    private static void demoScrolldown() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        ActionUtil.scrollVerticalPercent(0.69, 0.2);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        ActionUtil.scrollVerticalPercent(0.69, 0.2);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        ActionUtil.scrollToBeginning();
    }
}
