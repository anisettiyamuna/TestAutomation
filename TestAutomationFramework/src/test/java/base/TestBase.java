package base;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.nio.file.Files;
import java.text.SimpleDateFormat;

import java.util.Date;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import utilities.ExtentReportManager;

public class TestBase {

	public WebDriver driver;
	public WebDriverWait wait;
	public FileReader fr;
	public Properties prop = new Properties();
	public ExtentReports extent;
	public ExtentTest test;

	@BeforeTest
	public void setupReport() {
		System.out.println("Freemarker version: " + freemarker.template.Configuration.getVersion());
		extent = ExtentReportManager.getReportInstance();
	}

	@BeforeMethod
	public void setup() throws IOException, InterruptedException {

		if (driver == null) {
			fr = new FileReader(
					"C:\\Users\\ADMIN\\eclipse-workspace\\TestAutomation\\TestAutomationFramework\\src\\test\\resources\\configfiles\\config.properties");
			prop.load(fr);
		}

		if (prop.getProperty("browser").equalsIgnoreCase("chrome")) {
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--incognito");
			options.addArguments("start-maximized");// base
			WebDriverManager.chromedriver().setup(); // base
			driver = new ChromeDriver(options); // base
//			driver.manage().window().maximize();
//			driver.manage().deleteAllCookies();
			driver.get(prop.getProperty("practiceurl"));

			Thread.sleep(1000);
		} else if (prop.getProperty("browser").equalsIgnoreCase("firefox")) {
			ChromeOptions options = new ChromeOptions(); // base
			WebDriverManager.firefoxdriver().setup(); // base
			driver = new ChromeDriver(options); // base
			driver.manage().window().maximize();
			driver.get(prop.getProperty("testurl"));
		}

	}

	@AfterMethod
	public void tearDown(ITestResult result) throws IOException {
		if (result.getStatus() == ITestResult.FAILURE) {
			test.fail("Test Failed: " + result.getThrowable());

			String screenshotPath = takeScreenshot(result.getMethod().getMethodName());
			test.addScreenCaptureFromPath(screenshotPath);

		}
		driver.quit();
		System.out.println("Teardown Successful");
	}

	@AfterTest
	public void generateReport() {
		extent.flush();
	}

	public String takeScreenshot(String methodName) throws IOException {
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		String screenshotName = methodName + "_" + timestamp + ".png";
		String path = "reports/screenshots/" + screenshotName;
		Files.copy(src.toPath(), new File(path).toPath());
		return screenshotName;

	}

}