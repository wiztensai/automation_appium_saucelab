package core;

import core.driver.AppDriver;
import io.appium.java_client.AppiumDriver;

public class PrefsUtil {
    static private ThreadLocal <String> deviceName = new ThreadLocal<>();

    public static String getDeviceName() {
        return deviceName.get();
    }

    public static void setDeviceName(String deviceName) {
        PrefsUtil.deviceName.set(deviceName);
    }
}
