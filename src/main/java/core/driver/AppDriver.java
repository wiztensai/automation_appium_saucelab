package core.driver;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebDriver;

public class AppDriver {
    static private ThreadLocal <AppiumDriver> driver = new ThreadLocal<>();

    static public AppiumDriver getDriver() {
        return driver.get();
    }

    static public void setDriver(AppiumDriver driver) {
        AppDriver.driver.set(driver);
    }

    static public void quit() {
        driver.get().quit();
    }
}
