package controllers;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserFactory extends InitVariables {
    public static WebDriver driver;

    static WebDriver createDriver() throws Exception {

        switch (BROWSER.toLowerCase()) {
            case "chrome":
//                System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_EXE_PATH);
                WebDriverManager.chromedriver().setup();
            	driver = new ChromeDriver();
                break;

            case "chrome_headless":
                System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_EXE_PATH);
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--headless");
                chromeOptions.addArguments("--disable-gpu");
                driver = new ChromeDriver(chromeOptions);
                break;

            case "firefox":
               // System.setProperty("webdriver.gecko.driver", FIREFOX_DRIVER_EXE_PATH);
            	WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;

            case "edge":
              //  System.setProperty("webdriver.edge.driver", EDGE_DRIVER_EXE_PATH);
            	WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;

            case "safari":
                driver = new SafariDriver();
                break;

            default:
                throw new NotFoundException("Browser Not Found. Please Provide a Valid Browser");
        }

        if (IMPLICITLY_WAIT > 0) {
            implicitlywait(IMPLICITLY_WAIT);
        }

        if (MAX_PAGE_LOAD_TIME > 0) {
            setMaxPageLoadTime(MAX_PAGE_LOAD_TIME);
        }
        if (!BROWSER.toLowerCase().contains("unit") || !BROWSER.toLowerCase().contains("ghost") || !BROWSER.toLowerCase().contains("phantom")) {
            driver.manage().window().maximize();
        }
        driver.get(WEBPAGE_URL);
        
        return driver;
    }

    public static void implicitlywait(int timeInSeconds) throws Exception {
        driver.manage().timeouts().implicitlyWait(timeInSeconds, TimeUnit.SECONDS);
    }

    public static void setMaxPageLoadTime(int timeInSeconds) {
        driver.manage().timeouts().pageLoadTimeout(timeInSeconds, TimeUnit.SECONDS);
    }
}
