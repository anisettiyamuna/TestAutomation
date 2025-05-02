package base;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {

	public static WebDriver driver;
	public static FileReader fr;
	public static Properties prop = new Properties();

	@BeforeTest
	public void setup() throws IOException {

		if (driver == null) {
			fr = new FileReader(
					"C:\\Users\\ADMIN\\eclipse-workspace\\TestAutomation\\TestAutomationFramework\\src\\test\\resources\\configfiles\\config.properties");
			prop.load(fr);
		}

		if (prop.getProperty("browser").equalsIgnoreCase("chrome")) {
			ChromeOptions options = new ChromeOptions(); // base
			WebDriverManager.chromedriver().setup(); // base
			driver = new ChromeDriver(options); // base
			driver.manage().window().maximize();
			driver.get(prop.getProperty("testurl"));
		} else if (prop.getProperty("browser").equalsIgnoreCase("firefox")) {
			ChromeOptions options = new ChromeOptions(); // base
			WebDriverManager.firefoxdriver().setup(); // base
			driver = new ChromeDriver(options); // base
			driver.manage().window().maximize();
			driver.get(prop.getProperty("testurl"));
		}
	}
	

	@AfterTest
	public void teardown() {
		driver.close();
		System.out.println("Teardown Successful");
	}

}