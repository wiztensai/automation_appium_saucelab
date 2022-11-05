package core.driver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class WebDriverManager {
    public static WebDriver android() {
        UiAutomator2Options options = new UiAutomator2Options()
                .setDeviceName("Android Emulator")
                .setAppPackage("com.swaglabsmobileapp")
                .setAppActivity("com.swaglabsmobileapp.MainActivity")
                .setApp(System.getProperty("user.dir") + "/saucelab.apk")
                .eventTimings();

        return new AndroidDriver(AppServer.url(), options);
    }

    public static AppiumDriver android(String deviceName, String udid, String systemPort) {
        UiAutomator2Options options = new UiAutomator2Options()
                .setDeviceName(deviceName)
                .setUdid(udid)
//                .setSystemPort(Integer.parseInt(systemPort))
                .setAppPackage("com.swaglabsmobileapp")
                .setAppActivity("com.swaglabsmobileapp.MainActivity")
                .setApp(System.getProperty("user.dir") + "/saucelab.apk")
                .eventTimings();

        try {
            return new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), options);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
//        return new AndroidDriver(AppServer.url(), options);
    }
}
