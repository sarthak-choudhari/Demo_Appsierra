package controllers;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class WebDriverFactory extends BrowserFactory {
	public static ThreadLocal<WebDriver> wd = new ThreadLocal<WebDriver>();

	@BeforeMethod
	public void beforeClass() throws Exception {
		System.out.println("Browser: " + BROWSER);
		System.out.println("WebpageURL: " + WEBPAGE_URL);
		new WebDriverFactory();
		WebDriver driver = WebDriverFactory.createDriver();
		setWebDriver(driver);
	}

	public void setWebDriver(WebDriver driver) {
		wd.set(driver);
	}

	public static WebDriver getWebDriver() {
		return wd.get();
	}

	public static void saveFullPageScreenshot(String name) throws IOException {
		Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000))
				.takeScreenshot(getWebDriver());
		ImageIO.write(screenshot.getImage(), "PNG", new File(name));
	}

	public static void savePageScreenshot(String name) throws IOException {
		File f = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(f, new File(name));
	}

//	@AfterMethod(alwaysRun = true, enabled = true)
//	public void takeScreenshot(ITestResult result) throws Exception {
//		Thread.sleep(2000);
//		if (result.getStatus() == ITestResult.FAILURE) {
//			savePageScreenshot(
//					IMAGES_DIR + result.getTestClass().getName() + "." + result.getMethod().getMethodName() + ".png");
//		}
//	}

	@AfterMethod(alwaysRun = true, enabled = true)
	public void afterClass(ITestResult result) throws Exception {
		Thread.sleep(2000);
		if (result.getStatus() == ITestResult.FAILURE) {
			savePageScreenshot(
					IMAGES_DIR + result.getTestClass().getName() + "." + result.getMethod().getMethodName() + ".png");
		
	}
		getWebDriver().quit();
		}
}
