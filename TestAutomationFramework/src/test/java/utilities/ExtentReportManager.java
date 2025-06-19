package utilities;


import java.text.SimpleDateFormat;


import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager {

    private static ExtentReports extent;

    public static ExtentReports getReportInstance() {
        if (extent == null) {
            String path = System.getProperty("user.dir") + "/reports/ExtentReport.html";
            ExtentSparkReporter htmlreport = new ExtentSparkReporter(path);

            htmlreport.config().setTheme(Theme.DARK);  // or Theme.STANDARD
            htmlreport.config().setDocumentTitle("Automation Test Report");
            htmlreport.config().setReportName("Regression Suite");

            extent = new ExtentReports();
            extent.attachReporter(htmlreport);
            extent.setSystemInfo("QA Engineer", "Yamuna");
            extent.setSystemInfo("OS", System.getProperty("os.name"));
            String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            extent.setSystemInfo("Execution Time", timestamp);
        }
        return extent;
    }
    
    
}
