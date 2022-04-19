package Pages;

import Base.BasePage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class MainPage extends BasePage {
    private WebDriver driver;
    private static Logger logger = LoggerFactory.getLogger("MainPage.class");


    public MainPage(WebDriver driver) {
        super(driver);
        logger.info("########## MainPage is created");
    }

    @FindBy(xpath = "//div[@class='product-description']//h3")
    private List<WebElement> productsList;

    public String getRandomProductNameFromMainPage() {
        return chooseRandomValueFromList(productsList).getText();
    }

}
