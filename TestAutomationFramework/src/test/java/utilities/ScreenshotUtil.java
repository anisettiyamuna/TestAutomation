package utilities;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtil {

    public static String takeScreenshot(WebDriver driver, String methodName) {
        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String screenshotName = methodName + "_" + timestamp + ".png";
            String path = System.getProperty("user.dir") + "/reports/screenshots/" + screenshotName;
            Files.copy(src.toPath(), new File(path).toPath());
            return path;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
