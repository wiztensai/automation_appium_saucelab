package core.reporter;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.model.Media;

public class ExtentUtil {
    static public void logInfo(String msg) {
        if (msg != null) {
            ExtentReportObj.getTest().log(Status.INFO,msg);
        }
    }

    static public void logPass() {
        ExtentReportObj.getTest().log(Status.PASS,"Test Passed");
    }

    static public void logFail(Throwable t ) {
        if (t != null) {
            ExtentReportObj.getTest().log(Status.FAIL,t);
        }
    }

    static public void logFail(String t) {
        if (t != null) {
            ExtentReportObj.getTest().log(Status.FAIL,t);
        }
    }

    static public void logFail(Media media) {
        if (media != null) {
            ExtentReportObj.getTest().log(Status.FAIL,media);
        }
    }

    static public void setCategory(String... category) {
        if (category.length != 0) {
            ExtentReportObj.getTest().assignCategory(category);
        }
    }

    public static synchronized void updateReport() {
        ExtentReportObj.getReporter().flush();
    }
}
