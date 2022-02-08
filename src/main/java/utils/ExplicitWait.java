package utils;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import controllers.BaseMethod;

public class ExplicitWait extends BaseMethod {

    /*Select using WebElements*/

    /** To Wait Until Element to be Clickable  */
    public static WebElement explicitWaitElementToBeClickable(WebElement element, long timeInSeconds) {
        waitIgnoringStale(timeInSeconds).until(ExpectedConditions.elementToBeClickable(element));
        return element;
    }


    /** To Wait Until Element to be Selectable */
    public static WebElement explicitWaitElementToBeSelected(WebElement element, long timeInSeconds) {
        waitIgnoringStale(timeInSeconds).until(ExpectedConditions.elementToBeSelected(element));
        return element;
    }


    /** To Wait Until Element has the text */
    public static WebElement explicitWaitTextToBePresentInElement(WebElement element, long timeInSeconds, String text) {
        waitIgnoringStale(timeInSeconds).until(ExpectedConditions.textToBePresentInElement(element, text));
        return element;
    }


    /** To Wait Until Title contains the text */
    public static WebElement explicitWaitTitleContains(WebElement element, long timeInSeconds, String title) {
        waitIgnoringStale(timeInSeconds).until(ExpectedConditions.titleContains(title));
        return element;
    }


    /** To Wait Until Title is */
    public static WebElement explicitWaitTitleIs(WebElement element, long timeInSeconds, String title) {
        waitIgnoringStale(timeInSeconds).until(ExpectedConditions.titleIs(title));
        return element;
    }


    /** To Wait Until Element to be Visible */
    public static WebElement explicitWaitVisibilityOfElement(WebElement element, long timeInSeconds) {
        waitIgnoringStale(timeInSeconds).until(ExpectedConditions.visibilityOf(element));
        return element;
    }


    /** To Wait Until Element is Selected */
    public static WebElement explicitWaitSelectionStateToBe(WebElement element, long timeInSeconds, boolean selected) {
        waitIgnoringStale(timeInSeconds).until(ExpectedConditions.elementSelectionStateToBe(element, selected));
        return element;
    }


    /** To Wait Until Elements to be Visible */
    public static List<WebElement> explicitWaitVisibilityOfElements(List<WebElement> element, long timeInSeconds) {
        waitIgnoringStale(timeInSeconds).until(ExpectedConditions.visibilityOfAllElements(element));
        return element;
    }


    /*Select using By Method*/

    /** To Wait Until Element to be Clickable */
    public static WebElement explicitWaitElementToBeClickable(By element, long timeInSeconds) {
        waitIgnoringStale(timeInSeconds).until(ExpectedConditions.elementToBeClickable(element));
        return driver.findElement(element);
    }


    /** To Wait Until Element to be Selectable */
    public static WebElement explicitWaitElementToBeSelected(By element, long timeInSeconds) {
        waitIgnoringStale(timeInSeconds).until(ExpectedConditions.elementToBeSelected(element));
        return driver.findElement(element);
    }


    /** To Wait Until Title contains the text */
    public static WebElement explicitWaitTitleContains(By element, long timeInSeconds, String title) {
        waitIgnoringStale(timeInSeconds).until(ExpectedConditions.titleContains(title));
        return driver.findElement(element);
    }


    /** To Wait Until Title is */
    public static WebElement explicitWaitTitleIs(By element, long timeInSeconds, String title) {
        waitIgnoringStale(timeInSeconds).until(ExpectedConditions.titleIs(title));
        return driver.findElement(element);
    }


    /** To Wait Until Element to be Visible */
    public static WebElement explicitWaitVisibilityOfElement(By element, long timeInSeconds) {
        waitIgnoringStale(timeInSeconds).until(ExpectedConditions.visibilityOfElementLocated(element));
        return driver.findElement(element);
    }


    /** To Wait Until Element is Selected */
    public static WebElement explicitWaitSelectionStateToBe(By element, long timeInSeconds, boolean selected) {
        waitIgnoringStale(timeInSeconds).until(ExpectedConditions.elementSelectionStateToBe(element, selected));
        return driver.findElement(element);
    }


    /** To Wait for the Alert */
    public static void explicitWaitForAlert(long timeInSeconds) {
        waitIgnoringStale(timeInSeconds).until(ExpectedConditions.alertIsPresent());
    }
    
    private static FluentWait<WebDriver> waitIgnoringStale(long waitInSeconds) {
        return new WebDriverWait(driver, waitInSeconds)
        		.ignoring(StaleElementReferenceException.class)
        		.pollingEvery(Duration.ofMillis(500));
    } 
}
