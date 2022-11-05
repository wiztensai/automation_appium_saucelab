package core.util;

import core.driver.AppDriver;
import core.reporter.ExtentUtil;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;

import static java.time.Duration.ofMillis;
import static org.openqa.selenium.interactions.PointerInput.MouseButton.LEFT;
import static org.openqa.selenium.interactions.PointerInput.Origin.viewport;

public class ActionUtil {

    private static AppiumDriver driver() {
        return AppDriver.getDriver();
    }

    public static void waitForVisibility(By by) {
        WebDriverWait wait = new WebDriverWait(AppDriver.getDriver(), Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }
    public static void waitForVisibility(WebElement element) {
        WebDriverWait wait = new WebDriverWait(AppDriver.getDriver(), Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static void navigateBack() {
        driver().navigate().back();
    }

    public static void navigateForward() {
        driver().navigate().forward();
    }

    public static void click(WebElement element, String msg) {
        waitForVisibility(element);
        element.click();
        ExtentUtil.logInfo(msg);
    }

    public static void sendKeys(WebElement element, String txt, String msg) {
        waitForVisibility(element);
        element.sendKeys(txt);
        ExtentUtil.logInfo(msg);
    }

    public static void scrollIntoView(String Text, String msg){
        String parent = "new UiScrollable(new UiSelector().scrollable(true).instance(0))";
        String child = ".scrollIntoView(new UiSelector().text(\"" + Text + "\").instance(0));";
        String nativeCode = parent+child;
        var by = AppiumBy.androidUIAutomator(nativeCode);
        waitForVisibility(by);

        (AppDriver.getDriver()).findElement(by);

        ExtentUtil.logInfo(msg);
    }

    public static void scrollToContentDesc(String contentDesc, String msg) {
        String parent = "new UiScrollable(new UiSelector().scrollable(true).instance(0))";
        String child = ".scrollIntoView(new UiSelector().description(\"" + contentDesc + "\").instance(0));";
        String nativeCode = parent+child;
        var by = AppiumBy.androidUIAutomator(nativeCode);
        waitForVisibility(by);

        (AppDriver.getDriver()).findElement(by);

        ExtentUtil.logInfo(msg);
    }

    public static void selectDropdown(WebElement element, String text, String msg){
        waitForVisibility(element);

        var dropdown = new Select(element);
        dropdown.selectByVisibleText(text);

        ExtentUtil.logInfo(msg);
    }
    public static void scrollUp() {
        var driver = driver();

        Dimension dimension = driver.manage().window().getSize();
        Point start = new Point((int)(dimension.width*0.5), (int)(dimension.height*0.9));
        Point end = new Point((int)(dimension.width*0.2), (int)(dimension.height*0.1));
        doSwipe(driver, start, end, 1000);
    }

    /**
     * start: 0.8 * device height (hampir setinggi device)
     * end: 0.2 * device height
     */
    public static void scrollDown() {
        var driver = driver();

        Dimension dimension = driver.manage().window().getSize();
        Point start = new Point((int)(dimension.width*0.5), (int)(dimension.height*0.8));
        Point end = new Point((int)(dimension.width*0.5), (int)(dimension.height*0.2));
        doSwipe(driver, start, end, 1000);
    }

    public static void scrollVerticalPercent(double startPercent, double endPercent) {
        var driver = driver();

        Dimension dimension = driver.manage().window().getSize();
        System.out.println(dimension);
        Point start = new Point((int)(dimension.width*0.5), (int)(dimension.height*startPercent));
        Point end = new Point((int)(dimension.width*0.5), (int)(dimension.height*endPercent));

        System.out.println("start "+start);
        System.out.println("end "+end);
        doSwipe(driver, start, end, 1000);
    }

    public static void scrollToEnd() {
        driver().findElement(
            AppiumBy.androidUIAutomator(
    "new UiScrollable(new UiSelector().scrollable(true)).scrollToEnd(100000)")
        );
    }

    public static void scrollToBeginning() {
        driver().findElement(
                AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollToBeginning(1000)")
        );
    }

    public static void scrollVerticalPercent(double startPercent, double endPercent, AppiumDriver driver) {
        Dimension dimension = driver.manage().window().getSize();
        System.out.println(dimension);
        Point start = new Point((int)(dimension.width*0.5), (int)(dimension.height*startPercent));
        Point end = new Point((int)(dimension.width*0.5), (int)(dimension.height*endPercent));

        System.out.println("start "+start);
        System.out.println("end "+end);
        doSwipe(driver, start, end, 1000);
    }

    public static void scrollVerticalPercent(int startPercent, int endPercent) {
        var driver = driver();
        double mstart = startPercent / 100.0; // add .0 for dividen
        double mend = endPercent / 100.0;

        Dimension dimension = driver.manage().window().getSize();
        Point start = new Point((int)(dimension.width*0.5), (int)(dimension.height*mstart));
        Point end = new Point((int)(dimension.width*0.5), (int)(dimension.height*mend));
        doSwipe(driver, start, end, 1000);
    }

    public static void tapByPercent(int xPercent, int yPercent) {
        var driver = driver();
        double mX = xPercent / 100.0;
        double mY = yPercent / 100.0;

        Dimension dimension = driver.manage().window().getSize();
        Point tapPoint = new Point((int)(dimension.width*mX), (int)(dimension.height*mY));
        doTap(driver,tapPoint, 0);
    }

    public static void tapPoint(int x, int y) {
        var driver = driver();

        Dimension dimension = driver.manage().window().getSize();
        Point tapPoint = new Point(x, y);
        doTap(driver,tapPoint, 0);
    }

    private final static PointerInput FINGER = new PointerInput(PointerInput.Kind.TOUCH, "finger");

    public static void doSwipe(AppiumDriver driver, Point start, Point end, int duration) {
        Sequence swipe = new Sequence(FINGER, 1)
                .addAction(FINGER.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), start.getX(), start.getY()))
                .addAction(FINGER.createPointerDown(LEFT.asArg()))
                .addAction(FINGER.createPointerMove(ofMillis(duration), PointerInput.Origin.viewport(), end.getX(), end.getY()))
                .addAction(FINGER.createPointerUp(LEFT.asArg()));
        driver.perform(Arrays.asList(swipe));
    }

    private static void doTap(AppiumDriver driver, Point point, int duration) {
        Sequence tap = new Sequence(FINGER, 1)
                .addAction(FINGER.createPointerMove(ofMillis(0), viewport(), point.getX(), point.getY()))
                .addAction(FINGER.createPointerDown(LEFT.asArg()))
//                .addAction(new Pause(FINGER, ofMillis(duration)))
                .addAction(FINGER.createPointerUp(LEFT.asArg()));
        driver.perform(Arrays.asList(tap));
    }

    public static boolean isElementDisplayed(WebElement element){
        try{
            return element.isDisplayed();
        }catch(Exception e){
            return false;
        }
    }

    public static boolean theElementShouldNotShow(WebElement element){
        var show = isElementDisplayed(element);

        if(show) {
            return false;
        } else {
            return true;
        }
    }

    public static void reinstallApp(String apkPath, String pkgName) {
        var driver = ((AndroidDriver)AppDriver.getDriver());
        driver.removeApp(pkgName);
        driver.installApp(apkPath);
    }

    /**
     * Activates the given app if it installed, but not running or if it is running in the
     * background.
     */
    public static void openApp(String pkgName) {
        var driver = ((AndroidDriver)driver());
        driver.activateApp(pkgName);
    }

    /**
     * Terminate the particular application if it is running.
     */
    public static void closeApp(String pkgName) {
        var driver = ((AndroidDriver)driver());
        driver.terminateApp(pkgName);
    }

    public static void startMainActivity() {
        ((AndroidDriver)driver()).startActivity(new Activity("com.swaglabsmobileapp", "com.swaglabsmobileapp.MainActivity"));
    }

    public static void startMainActivity(String appPackage, String appActivity) {
        ((AndroidDriver)driver()).startActivity(new Activity(appPackage, appActivity));
    }

    static public String takeScreenshot(WebDriver driver) {
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        return screenshot.getScreenshotAs(OutputType.BASE64);
    }
}