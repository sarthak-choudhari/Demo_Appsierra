package controllers;

import java.awt.Robot;
import java.io.File;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.Alert;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestResult;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class InitVariables {

	static {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_hh-mm-ss");
		System.setProperty("current.date", dateFormat.format(new Date()));
		DOMConfigurator.configure("log4j.xml");
	}

	public static PropertiesFileReader appConfig = PropertiesFileReader
			.getInstance("src/main/java/resources/ApplicationConfig.properties");
	public static final String WEBPAGE_URL = appConfig.getProperty("Url");
	public static final String TOWER_URL = appConfig.getProperty("TowerUrl");
	public static final String BROWSER = appConfig.getProperty("Browser");
	public static final String EMAIL_ID = appConfig.getProperty("Email");
	public static final String PASSWORD = appConfig.getProperty("Password");
	public static final String TESTPASSWORD = appConfig.getProperty("TestPassword");
	public static String MAILURL = appConfig.getProperty("MailURL");
	public static final int MAX_PAGE_LOAD_TIME = Integer.parseInt(appConfig.getProperty("MaxPageLoadTime"));
	public static final int IMPLICITLY_WAIT = Integer.parseInt(appConfig.getProperty("ImplicitlyWait"));
	public static final String FILE_SEPARATOR = File.separator;

	public static final int WAIT_10_SEC = 10;
	public static final int WAIT_20_SEC = 20;
	public static final int WAIT_30_SEC = 30;
	public static final int WAIT_40_SEC = 40;
	public static final int WAIT_50_SEC = 50;
	public static final int WAIT_60_SEC = 60;

	public static final String OS_NAME = System.getProperty("os.name");
	public static final String OS_ARCHITECTURE = System.getProperty("os.arch");
	public static final String OS_VERSION = System.getProperty("os.version");
	public static final String OSBit = System.getProperty("sun.arch.data.model");
	public static final String USER_DIRECTORY = System.getProperty("user.dir");
	public static final String CHROME_DRIVER_EXE_PATH = "src/main/java/resources/drivers/chromedriver.exe";
	public static final String FIREFOX_DRIVER_EXE_PATH = "src/main/java/resources/drivers/geckodriver.exe";
	public static final String EDGE_DRIVER_EXE_PATH = "src/main/java/resources/drivers/MicrosoftWebDriver.exe";

	public static final String TEST_DATA_DIR = USER_DIRECTORY + "/src/test/resources/TestData/";
	public static final String EXCEL_FILES_DIR = USER_DIRECTORY + "/src/test/resources/TestData/Excel Files/";
	public static final String PROPERTIES_FILES_DIR = USER_DIRECTORY + "/src/test/resources/TestData/Properties Files/";
	public static final String REPORTS_DIR = USER_DIRECTORY + "/src/test/resources/Reports/";
	public static final String IMAGES_DIR = USER_DIRECTORY + "\\src\\test\\resources\\Reports\\Images\\";
	public static final String FRONTSIDEIDPROOF="C:\\Users\\Admin\\Desktop\\AppsIerra\\automated-testing\\src\\test\\resources\\Test Data\\TestDocuments\\realIDCardFrontSide.png";
	public static final String BACKSIDEIDPROOF="C:\\Users\\Admin\\Desktop\\AppsIerra\\automated-testing\\src\\test\\resources\\Test Data\\TestDocuments\\realIDCardBackSide.png";
	public static final String UTILITYBILL="C:\\Users\\Admin\\Desktop\\AppsIerra\\automated-testing\\src\\test\\resources\\Test Data\\TestDocuments\\realUtilityBill.png";

	
	public static Robot robot;
	public static Alert alert;
	public static String robotImageName;
	public static Select select;
	public static String FileToUpload;
	public static Actions action;
	public static ITestResult testResult;
	public static SoftAssert softAssert;
	public static ITestResult result;
	public static URI uri;
//	public static JavascriptExecutor js;

	public static final String OUTPUT_FOLDER = "./src/test/resources/Reports/";
	public static final String REPORT_FILE_NAME = "Extent Report.html";
	public ExtentReports extent;
	public ISuite suite;
	public ISuiteResult res;
	public ExtentTest test;

}
