package Pages;

import Base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AccountPage extends BasePage {
    private WebDriver driver;
    private static Logger logger = LoggerFactory.getLogger("AccountPage.class");

    public AccountPage(WebDriver driver) {
        super(driver);
        logger.info("########## AccountPage is created");
    }

    @FindBy(xpath = "//a[@id='history-link']/span")
    private WebElement orderHistoryLink;

    public void clickOnOrderHistoryLink(){
        orderHistoryLink.click();
        logger.info("<<<<<<<<<< Click on orderHistory link");
    }
}
