package extentReports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class extentReportsClass {
    ExtentReports extent;

    public static ExtentReports extentReport() {
    String path = System.getProperty("user.dir") + "\\Reports\\index.html";
    ExtentSparkReporter UI = new ExtentSparkReporter(path);
    UI.config().setReportName("EKART_UDEMY_POM");
    UI.config().setDocumentTitle("Reports");

    ExtentReports extent= new ExtentReports();
    extent.attachReporter(UI);

    return extent;

}


}
