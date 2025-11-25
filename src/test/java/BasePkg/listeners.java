package BasePkg;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import extentReports.extentReportsClass;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import java.io.IOException;

public class listeners extends Base implements ITestListener {

    ExtentReports extent= extentReportsClass.extentReport();
    ExtentTest test;
    @Override
    public void onTestStart(ITestResult result) {
        Reporter.log("TEST STARTED → " + result.getName(), true);
       test= extent.createTest(result.getMethod().getMethodName());

    }


    @Override
    public void onTestSuccess(ITestResult result) {
        Reporter.log("TEST PASSED → " + result.getName(), true);
        test.log(Status.PASS,"Test Passed"+result.getName());
    }


    @Override
    public void onTestFailure(ITestResult result) {
        Reporter.log("TEST FAILED → " + result.getName(), true);
        test.log(Status.FAIL,"Test Failed: " + result.getName());
        test.fail(result.getThrowable());

        try {
            driver = (WebDriver) result.getInstance()
                    .getClass()
                    .getField("driver")
                    .get(result.getInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            String screenshotPath = getScreenShot(result.getName(), driver);
            test.addScreenCaptureFromPath(screenshotPath, result.getMethod().getMethodName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onTestSkipped(ITestResult result) {
        Reporter.log("TEST SKIPPED → " + result.getName(), true);
    }


    @Override
    public void onStart(ITestContext context) {
        Reporter.log("SUITE STARTED → " + context.getName(), true);
    }


    @Override
    public void onFinish(ITestContext context) {
        Reporter.log("SUITE FINISHED → " + context.getName(), true);
        extent.flush();
    }

}
