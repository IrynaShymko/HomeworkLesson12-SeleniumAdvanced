package Pages;

import Base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SearchResultsPage extends BasePage {
    private WebDriver driver;
    private TopPanelPage topPanelPage;
    private static Logger logger = LoggerFactory.getLogger("SearchResultsPage.class");

    public SearchResultsPage(WebDriver driver) {
        super(driver);
        this.topPanelPage = new TopPanelPage(driver);
        logger.info("########## SearchResultsPage is created");
    }

    @FindBy(xpath = "//div[@class='product-description']//h2/a")
    private WebElement productsSearchResult;


    public String getNameFoundedProduct(){
        wait.until(ExpectedConditions.visibilityOf(productsSearchResult));
        logger.info("<<<<<<<<<< NameFoundedProduct is: "+ productsSearchResult.getText());
        return productsSearchResult.getText();
    }
}
