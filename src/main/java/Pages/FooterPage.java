package Pages;

import Base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FooterPage extends BasePage {
    private WebDriver driver;
    private static Logger logger = LoggerFactory.getLogger("FooterPage.class");

    public FooterPage(WebDriver driver) {
        super(driver);
        logger.info("########## FooterPage is created");
    }

    @FindBy(xpath = "//a[@id='link-product-page-prices-drop-1']")
    private WebElement pricesDropLink;

    public void clickOnLinkPricesDrop() {
        clickOnElement(pricesDropLink);
    }


}


