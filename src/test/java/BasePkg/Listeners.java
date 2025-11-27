package BasePkg;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import extentReports.ExtentReportsClass;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

public class Listeners extends BaseTest implements ITestListener {

    ExtentReports extent= ExtentReportsClass.extentReport();
    ExtentTest test;
    ThreadLocal<ExtentTest> extentThread = new ThreadLocal<ExtentTest>();
    @Override
    public void onTestStart(ITestResult result) {
        Reporter.log("TEST STARTED → " + result.getName(), true);
       test= extent.createTest(result.getMethod().getMethodName());
       extentThread.set(test);

    }


    @Override
    public void onTestSuccess(ITestResult result) {
        Reporter.log("TEST PASSED → " + result.getName(), true);
        extentThread.get().log(Status.PASS,"Test Passed"+result.getName());
    }


    @Override
    public void onTestFailure(ITestResult result) {
        Reporter.log("TEST FAILED → " + result.getName(), true);
        test.log(Status.FAIL,"Test Failed: " + result.getName());
        extentThread.get().fail(result.getThrowable());

        try {
            driver = (WebDriver) result.getInstance()
                    .getClass()
                    .getField("driver")
                    .get(result.getInstance());
            String screenshotPath = getScreenShot(result.getName(), driver);
            extentThread.get().addScreenCaptureFromPath(screenshotPath, result.getMethod().getMethodName());
        } catch (Exception e) {
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
