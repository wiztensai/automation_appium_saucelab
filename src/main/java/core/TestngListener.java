package core;

import com.aventstack.extentreports.MediaEntityBuilder;
import core.driver.AppDriver;
import core.reporter.ExtentReportObj;
import core.reporter.ExtentUtil;
import core.util.ActionUtil;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestngListener implements ITestListener {
    @Override
    public void onTestStart(ITestResult result) {
//        var threadId = Thread.currentThread().getId();
//        System.out.println(result.getName()+" onTestStart threadId "+threadId);

        ExtentReportObj.startTest(result.getName(), result.getMethod().getDescription())
                .assignAuthor("Nanang Fitrianto")
                .assignCategory(PrefsUtil.getDeviceName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentUtil.logPass();
    }

    @Override
    public void onTestFailure(ITestResult result) {
        var ss = ActionUtil.takeScreenshot(AppDriver.getDriver());
        var media = MediaEntityBuilder.createScreenCaptureFromBase64String(ss).build();

        ExtentUtil.logFail(media);

        ExtentUtil.logFail(result.getThrowable());
    }

    @Override
    public void onFinish(ITestContext context) {
        ExtentUtil.updateReport();
    }
}
