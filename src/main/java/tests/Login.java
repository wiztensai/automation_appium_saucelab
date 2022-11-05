package tests;

import core.BaseTest;
import core.driver.AppDriver;
import core.reporter.ExtentUtil;
import core.util.ActionUtil;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.LoginPage;
import pages.ProductListPage;

public class Login extends BaseTest {
    LoginPage loginPage;
    ProductListPage productListPage;

    @BeforeClass
    public void beforeClass() {
        loginPage = new LoginPage();
        productListPage = new ProductListPage();
    }

    @Test(alwaysRun = true)
    public void invalidLogin() {
        loginPage.login("standard_user", "asdf");

        var errorMessage = loginPage.getErrorMessage();
        Assert.assertTrue(errorMessage.contains("do not match"));
    }

    @Test(dependsOnMethods = "invalidLogin", alwaysRun = true)
    public void validLogin() {
        loginPage.login("standard_user", "secret_sauce");
    }

    @Test(dependsOnMethods = "validLogin", alwaysRun = true)
    public void logout() {
        productListPage.clickBtnHamburger();
        productListPage.clickLogoutButton();

        Assert.assertTrue(ActionUtil.isElementDisplayed(loginPage.btnLogin));
    }
}
