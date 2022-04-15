package Properties;

import model.Environment;

public class Configuration {
    public Environment environment;
    public BrowserConfig browserConfig;
    public BrowserProperties browserProperties;

    public Environment getEnvironment() {
        return environment;
    }

    public BrowserConfig getBrowserConfig() {
        return browserConfig;
    }

    public BrowserProperties getBrowserProperties() {
        return browserProperties;
    }
}
