package Pages;

import Base.BasePage;
import Helpers.ProductBox;
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

    @FindBy(xpath = "//a[contains(text(), 'Art')]")
    private WebElement artCategory;

    @FindBy(xpath = "//a[@class='dropdown-item']")
    private List<WebElement> categoriesList;

    @FindBy(xpath = "//span[@class='cart-products-count']")
    private WebElement quantityProductsOnCartIcon;

    @FindBy(xpath = "//span[contains(text(), 'Cart')]")
    private WebElement cartIcon;

    @FindBy(xpath = "//a[@title='Log in to your customer account']")
    private WebElement signInButton;

    @FindBy(xpath = "//a[@title='View my customer account']")
    private WebElement accountLink;

    public TopPanelPage fillSearchField(String value) {
        sendKeys(searchField, value);
        return this;
    }

    public void clickSearchButton() {
        clickOnElement(searchButton);
    }

    public void searchProduct(String value) {
        fillSearchField(value);
        clickSearchButton();
    }

    public String getProposedProductNameFromDropdown() {
        wait.until(ExpectedConditions.visibilityOf(proposedProductInDropdownQuickSearch));
        return proposedProductInDropdownQuickSearch.getText();
    }

    public List<WebElement> getCategoriesList() {
        return categoriesList;
    }

    public void navigateToArtCategory() {
        clickOnElement(artCategory);
    }

    public void chooseRandomCategory() {
        chooseRandomValueFromList(categoriesList).click();
    }


    public Boolean isQuantityOnCartIconOnTopPanelTheSameInBox(ProductBox productBox) {
        int quantityOnTopPanel = Integer.parseInt(quantityProductsOnCartIcon.getText().substring(1, quantityProductsOnCartIcon.getText().indexOf(")")));
        int quantityInBox = productBox.getTotalQuantityProductsInBox();
        logger.info("<<<<<<<<<< quantityOnTopPanel: " + quantityOnTopPanel);
        logger.info("<<<<<<<<<< quantityInBox: " + quantityInBox);

        if (quantityOnTopPanel == quantityInBox) {
            return true;
        } else return false;
    }

    public void clickCartIcon(){
        wait.until(ExpectedConditions.elementToBeClickable(cartIcon));
        hoverAndDoubleClick(cartIcon);
    }

    public void clickSignInButton(){
        signInButton.click();
        logger.info("<<<<<<<<<< Click SignIn button");
    }

    public void navigateToAccountPage(){
        accountLink.click();
        logger.info("<<<<<<<<<< Navigate to account page");
    }

}



