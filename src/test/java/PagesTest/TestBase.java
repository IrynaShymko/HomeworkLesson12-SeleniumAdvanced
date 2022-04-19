package PagesTest;
import Base.BasePage;
import Pages.MainPage;
import Pages.TopPanelPage;
import Properties.AppProperties;
import Properties.BrowserEnvironment;
import Properties.readers.YMLreader;
import helpers.DriverFactory;
import org.junit.jupiter.api.AfterAll;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.jupiter.api.BeforeAll;

import org.openqa.selenium.WebDriver;

public class TestBase extends BasePage {
    private static Logger logger = LoggerFactory.getLogger("PagesTest.TestBase.class");
    protected static WebDriver driver;
    private static AppProperties appProperties;
    private static BrowserEnvironment browserEnvironment;
    MainPage mainPage = new MainPage(driver);
    TopPanelPage topPanelPage=new TopPanelPage(driver);

    public TestBase (WebDriver driver) {
        super(driver);
        logger.info("########## TestBase page is created");
    }

    @BeforeAll
    static void beforeAll() {
        appProperties = new AppProperties();
        browserEnvironment = new BrowserEnvironment();
        driver=new DriverFactory().getDriver(new YMLreader().getConfiguration().getBrowserConfig().getBrowserEnum());
        logger.info("<<<<<<<<<<<<<<<<<< Driver initiated properly");
    }

    @AfterAll
    static void tearDown() {
        driver.quit();
        logger.info("<<<<<<<<<<<<<<<<<<Driver closed properly");
    }

}