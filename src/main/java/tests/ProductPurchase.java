package tests;

import core.BaseTest;
import core.reporter.ExtentUtil;
import core.util.ActionUtil;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.*;

public class ProductPurchase extends BaseTest {
    LoginPage loginPage;
    ProductListPage plp;
    CartPage cartPage;
    CheckoutInfoPage checkoutInfoPage;
    CheckoutOverviewPage checkoutOverviewPage;

    CheckoutCompletePage checkoutCompletePage;

    @BeforeClass
    public void setup() {
        ActionUtil.startMainActivity();

        loginPage = new LoginPage();
        plp = new ProductListPage();
        cartPage = new CartPage();
        checkoutInfoPage = new CheckoutInfoPage();
        checkoutOverviewPage = new CheckoutOverviewPage();
        checkoutCompletePage = new CheckoutCompletePage();
    }


    @Test(alwaysRun=true)
    public void makingUnselectedOrder() {
        loginPage.login("standard_user", "secret_sauce");
        plp.clickCartButton("Click cart button in top navigation");
        cartPage.clickCheckoutButton("Click 'CHECKOUT' button in YOUR CART page");

        var res = ActionUtil.theElementShouldNotShow(checkoutInfoPage.getCheckoutInformationTitle());
        Assert.assertTrue(res, "If cart empty, it should not go to checkout: information page!");
    }

    @Test(dependsOnMethods = "makingUnselectedOrder", alwaysRun=true)
    public void makingOrder() {
        ActionUtil.startMainActivity();

        loginPage.login("standard_user", "secret_sauce");

        plp.selectFirstItem("Click 'ADD TO CART' button to first product in PLP");
        plp.clickCartButton("Click cart button in top navigation");

        cartPage.clickCheckoutButton("Click 'CHECKOUT' button in YOUR CART page");

        checkoutInfoPage.inputFirstName("asd", "Input 'asd' to 'First Name' textbox in CHECKOUT: YOUR INFORMATION page");
        checkoutInfoPage.inputLastName("asd", "Input 'asd' to 'Last Name' textbox");
        checkoutInfoPage.inputZip("1234", "Input '1234' to ZIP' textbox");
        checkoutInfoPage.clickContinueBtn("Click continue button");

        checkoutOverviewPage.scrollToBottom("Scroll to bottom");
        checkoutOverviewPage.clickFinishBtn("Click 'CONTINUE' button in CHECKOUT: OVERVIEW page");

        var checkoutMsg = checkoutCompletePage.getCheckoutMsg();
        Assert.assertTrue(checkoutMsg.contains("Your order has been dispatched"));
    }
}
