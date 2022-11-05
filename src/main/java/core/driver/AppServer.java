package core.driver;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

import java.net.URL;

public class AppServer {
    private static AppiumDriverLocalService service;

    public static void start() {
        service = new AppiumServiceBuilder()
                .withArgument(GeneralServerFlag.BASEPATH, "/wd/hub")
                .withIPAddress("127.0.0.1")
                .usingPort(4724)
                .build();
        service.start();
    }

    public static URL url() {
        if (service != null) {
            return service.getUrl();
        } else {
            System.out.println("Cannot get URL because Appium server is null");
            return null;
        }
    }

    public static void stop() {
        if (service != null) {
            service.stop();
        } else {
            System.out.println("Cannot stop server because Appium server is null");
        }
    }
}
