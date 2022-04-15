package Properties;

import Properties.readers.YMLreader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BrowserEnvironment {
    private static int webElementTimeOut;
    private static int webBrowserImplicitTimeOut;

    private static Logger logger = LoggerFactory.getLogger("Properties.BrowserEnvironment.class");

    public BrowserEnvironment() {
        this.webElementTimeOut = 10;
        this.webBrowserImplicitTimeOut = 12;
        this.initBrowserSettings();
    }

    private void initBrowserSettings() {
        YMLreader ymLreader = new YMLreader();
        this.webElementTimeOut = ymLreader.getConfiguration().getBrowserProperties().getWebElementTimeOut() != 0 ? ymLreader.getConfiguration().getBrowserProperties().getWebElementTimeOut() : this.webElementTimeOut;
        this.webBrowserImplicitTimeOut = ymLreader.getConfiguration().getBrowserProperties().getWebBrowserImplicitTimeOut() != 0 ? ymLreader.getConfiguration().getBrowserProperties().getWebBrowserImplicitTimeOut() : this.webBrowserImplicitTimeOut;
    }


    public static int getWebElementTimeOut() {
        return webElementTimeOut;
    }

    public static int getWebBrowserImplicitTimeOut() {
        return webBrowserImplicitTimeOut;
    }


}
