package helpers;

import Properties.readers.YMLreader;
import io.github.bonigarcia.wdm.WebDriverManager;
import model.BrowserEnum;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class DriverFactory {
    private WebDriver driver;
    private static Logger logger = LoggerFactory.getLogger("DriverFactory.class");

    public WebDriver getDriver(BrowserEnum browserEnum) {
        switch (browserEnum) {
            case CHROME:
                startChromeBrowser();
                break;
            case FIREFOX:
                startFirefoxBrowser();
                break;
            case EDGE:
                startEdgeBrowser();
                break;
            case IE:
                startInternetExplorerBrowser();
                break;
            default:
                logger.info("DEFAULT BROWSER IS STARTED");
                startChromeBrowser();
                break;
        }
        return this.driver;
    }

    public void startChromeBrowser(){
        ChromeOptions optionsChrome = new ChromeOptions();
        WebDriverManager.chromedriver().setup();
        optionsChrome.addArguments("start-maximized");
        driver = new ChromeDriver(optionsChrome);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(new YMLreader().getConfiguration().getBrowserProperties().getWebBrowserImplicitTimeOut()));
        logger.info("BROWSER IS CHROME");
        driver.get(System.getProperty("appUrl"));
    }
    public void startFirefoxBrowser(){
        FirefoxOptions optionsFirefox = new FirefoxOptions();
        WebDriverManager.firefoxdriver().setup();
        optionsFirefox.addArguments("start-maximized");
        driver = new FirefoxDriver(optionsFirefox);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(new YMLreader().getConfiguration().getBrowserProperties().getWebBrowserImplicitTimeOut()));
        logger.info("BROWSER IS FIREFOX");
        driver.get(System.getProperty("appUrl"));
    }
    public void startEdgeBrowser(){
        EdgeOptions optionsEdge = new EdgeOptions();
        WebDriverManager.edgedriver().setup();
        optionsEdge.addArguments("start-maximized");
        driver = new EdgeDriver(optionsEdge);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(new YMLreader().getConfiguration().getBrowserProperties().getWebBrowserImplicitTimeOut()));
        logger.info("BROWSER IS EDGE");
        driver.get(System.getProperty("appUrl"));
    }

    public void startInternetExplorerBrowser(){
        InternetExplorerOptions optionsIE = new InternetExplorerOptions();
        WebDriverManager.iedriver().setup();
        driver = new InternetExplorerDriver(optionsIE);
        logger.info("BROWSER IS InternetExplorer");
        driver.get(System.getProperty("appUrl"));

    }

}
