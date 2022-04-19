package Base;

import Properties.BrowserEnvironment;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;
import java.util.Random;


public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Actions actions;
    private static Logger logger = LoggerFactory.getLogger("BasePage.class");

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(BrowserEnvironment.getWebElementTimeOut()));
        actions = new Actions(driver);
        PageFactory.initElements(driver, this);
    }


    public void clickOnElement(WebElement webElement) {
        webElement.click();
        logger.info("<<<<<<<<<< Click on element: " + webElement.getText());
    }

    public void hoverAndDoubleClick(WebElement webElement) {
        actions = new Actions(driver);
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
        actions.moveToElement(webElement).perform();
        actions.moveToElement(webElement).doubleClick().perform();
        logger.info("<<<<<<<<<< Hover and double click on element: " + webElement.getText());
    }

    public String getTextFromAlert() {
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        logger.info("<<<<<<<<<< Got text from alert: " + alert.getText());
        return alert.getText();
    }

    public void acceptAlert() {
        Alert alert = driver.switchTo().alert();
        alert.accept();
        logger.info("<<<<<<<<<< Alert accepted");
    }

    public void clearFieldAndSendKeys(WebElement webElement, String value) {
        webElement.clear();
        webElement.sendKeys(value);
        logger.info("<<<<<<<<<< Send keys on element: " + webElement.getText() + "Value is: " + value);
    }

    public void sendKeys(WebElement webElement, String value) {
        webElement.sendKeys(value);
        logger.info("<<<<<<<<<< Send keys Value is: " + value);
    }

    public WebElement chooseRandomValueFromList(List<WebElement> webElements) {
        int index = new Random().nextInt(webElements.size());
        return webElements.get(index);
    }

    public void switchToLastOpenedWindow() {
        for (String windowHandle : driver.getWindowHandles()) {
            driver.switchTo().window(windowHandle);
        }
        logger.info("<<<<<<<<<< Switch to last opened window");
    }

    public void scrollToElement(WebElement webElement) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();"
                , webElement);
        logger.info("<<<<<<<<<< Scroll to element: " + webElement.getText());
    }
}
