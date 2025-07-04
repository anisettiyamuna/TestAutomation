package listeners;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import utilities.ExtentReportManager;
import utilities.ScreenshotUtil;

//ITestListener interface which implements Testng listeners
public class ExtentReportListeners implements ITestListener, ISuiteListener {

	private static ExtentReports extent = ExtentReportManager.getReportInstance();
	private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
		test.set(extentTest);
	}

	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		test.get().pass("Test Passed");
	}

	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		// screenshot code
		// response if API is failed

		test.get().fail(result.getThrowable());
		WebDriver driver = (WebDriver) result.getTestContext().getAttribute("driver");

		String screenshotPath = ScreenshotUtil.takeScreenshot(driver, result.getMethod().getMethodName());
		test.get().fail("Screenshot of failure:").addScreenCaptureFromPath(screenshotPath);

	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		test.get().skip("Test Skipped: " + result.getThrowable());

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		extent.flush();
	}

}
