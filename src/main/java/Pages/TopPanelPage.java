package Pages;

import Base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class TopPanelPage extends BasePage {
    private WebDriver driver;
    private static Logger logger = LoggerFactory.getLogger("TopPanelPage.class");

    public TopPanelPage(WebDriver driver) {
        super(driver);
        logger.info("########## TopPanelPage is created");
    }

    @FindBy(xpath = "//input[@aria-label='Search']")
    private WebElement searchField;

    @FindBy(xpath = "//button[@type='submit']/i")
    private WebElement searchButton;

    @FindBy(xpath = "//ul[@id='ui-id-1']/li")
    private WebElement proposedProductInDropdownQuickSearch;

    @FindBy(xpath = "//a[@class='dropdown-item']")
    private List <WebElement> categoriesList;

    public TopPanelPage fillSearchField(String value){
        sendKeys(searchField, value);
        return this;
    }

    public void clickSearchButton(){
        clickOnElement(searchButton);
    }

    public void searchProduct(String value) {
        fillSearchField(value);
        clickSearchButton();
    }

    public String getProposedProductNameFromDropdown(){
        wait.until(ExpectedConditions.visibilityOf(proposedProductInDropdownQuickSearch));
        return proposedProductInDropdownQuickSearch.getText();
    }

    public List <WebElement> getCategoriesList(){
        return categoriesList;
    }


}
