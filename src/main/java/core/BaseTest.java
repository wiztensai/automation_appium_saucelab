package core;

import core.driver.AppDriver;
import core.driver.AppServer;
import core.driver.WebDriverManager;
import org.testng.annotations.*;

import java.time.Duration;

public class BaseTest {

    @BeforeSuite
    public void startServer() {
//        AppServer.start();
    }

    @Parameters({"deviceName","udid", "systemPort"})
    @BeforeTest
    public void setup(String deviceName, String udid, String systemPort) {
        var driver = WebDriverManager.android(deviceName, udid,systemPort);
        AppDriver.setDriver(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        PrefsUtil.setDeviceName(deviceName);
    }

    @AfterTest
    public void tearDown() {
        AppDriver.quit();
    }

    @AfterSuite
    public void stopServer() {
//        AppServer.stop();
    }
}
