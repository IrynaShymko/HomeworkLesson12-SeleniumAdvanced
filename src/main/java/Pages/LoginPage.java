package Pages;

import Base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginPage extends BasePage {
    private WebDriver driver;
    private static Logger logger = LoggerFactory.getLogger("LoginPage.class");

    public LoginPage(WebDriver driver) {
        super(driver);
        logger.info("########## LoginPage is created");
    }

    @FindBy(xpath = "//*[contains(text(), 'No account')]")
    private WebElement createAccountLink;

    public void clickCreateAccountLink() {
        createAccountLink.click();
        logger.info("<<<<<<<<<< Navigate to Registration form");
    }
}
