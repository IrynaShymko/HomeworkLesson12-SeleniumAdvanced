package PagesTest;

import Properties.AppProperties;
import Properties.BrowserConfig;
import Properties.BrowserEnvironment;
import Properties.Configuration;
import Properties.readers.YMLreader;
import helpers.DriverFactory;

import org.junit.jupiter.api.AfterAll;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.jupiter.api.BeforeAll;

import org.openqa.selenium.WebDriver;

public class TestBase {
    private static Logger logger = LoggerFactory.getLogger("PagesTest.TestBase.class");
    protected static WebDriver driver;
    private static AppProperties appProperties;
    private static BrowserEnvironment browserEnvironment;
    private static BrowserConfig browserConfig;
    private static Configuration configuration;


    @BeforeAll
    static void BeforeAll() {
        appProperties = new AppProperties();
        browserEnvironment = new BrowserEnvironment();
        driver=new DriverFactory().getDriver(new YMLreader().getConfiguration().getBrowserConfig().getBrowserEnum());
        logger.info("<<<<<<<<<<<<<<<<<< Driver started successfully");
        logger.info("<<<<<<<<<<<<<<<<<< NOTE: Test is executed on environment: "+System.getProperty("env_name"));
    }

    @AfterAll
    static void tearDown() {
        driver.quit();
        logger.info("<<<<<<<<<<<<<<<<<<Driver closed properly");
    }
}