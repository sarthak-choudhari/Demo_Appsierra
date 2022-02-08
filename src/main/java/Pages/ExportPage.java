package Pages;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;

import commons.BasePage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ExportPage extends BasePage {

	public static File folder;

	public ExportPage(WebDriver driver) {
		super(driver);
	}

	@SuppressWarnings("deprecation")
	public WebDriver setUpWebDriver() {

		folder = new File("downloads");
		folder.mkdir();

		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();

		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("profile.default_content_settings.popups", 0);
		prefs.put("download.default_directory", folder.getAbsolutePath());

		options.setExperimentalOption("prefs", prefs);
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);

		driver = new ChromeDriver(capabilities);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		return driver;
	}

	public void againHittingUrl() {

		driver.get("https://www.barcodesinc.com/");
	}

	public void downloadingFile() throws IOException {

		WebElement ExportToCSV = driver.findElement(By.xpath("//div[@class='buttons-bottom']//button[contains(text(),'Export to CSV')]"));
		wait.forElementToBeVisible(ExportToCSV);
		ExportToCSV.click();
		pause(2000);

		File listOfFiles[] = folder.listFiles();
		Assert.assertTrue(listOfFiles.length > 0);

		for (File file : listOfFiles) {
			Assert.assertTrue(file.length() > 0);
		}

		for (File file : folder.listFiles())
			file.delete();
		folder.delete();
		if (folder.isDirectory() == true)
			folder.delete();
	}
}