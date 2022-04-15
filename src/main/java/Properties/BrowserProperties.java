package Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class BrowserProperties {
    private int webElementTimeOut;
    private int webBrowserImplicitTimeOut;
    private static Logger logger = LoggerFactory.getLogger("BrowserProperties.class");

    public int getWebElementTimeOut() {
        return webElementTimeOut;
    }

    public int getWebBrowserImplicitTimeOut() {
        return webBrowserImplicitTimeOut;
    }

}
