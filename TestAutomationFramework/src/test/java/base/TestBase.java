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
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import utilities.ExtentReportManager;

public class TestBase {

	public WebDriver driver;
	public WebDriverWait wait;
	public FileReader fr;
	public Properties prop = new Properties();

	@BeforeMethod
	@Parameters("loginUrl")
	public void setup(String loginUrl) throws IOException, InterruptedException {

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
			driver.get(loginUrl);

			Thread.sleep(1000);
		} else if (prop.getProperty("browser").equalsIgnoreCase("firefox")) {
			ChromeOptions options = new ChromeOptions(); // base
			WebDriverManager.firefoxdriver().setup(); // base
			driver = new ChromeDriver(options); // base
			driver.manage().window().maximize();
			driver.get(loginUrl);

//			driver.get(prop.getProperty("practiceurl"));
		}

	}

	@AfterMethod
	public void tearDown() throws IOException {

		driver.quit();
		System.out.println("Teardown Successful");
	}

}