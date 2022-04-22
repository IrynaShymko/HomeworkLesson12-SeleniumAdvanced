package Pages;

import Base.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class CategoryPage extends BasePage {
    private WebDriver driver;
    private static Logger logger = LoggerFactory.getLogger("CategoryPage.class");

    public CategoryPage(WebDriver driver) {
        super(driver);
        logger.info("########## CategoryPage is created");
    }

    @FindBy(xpath = "//h1")
    private WebElement categoryTitle;

    @FindBy(xpath = "//p[contains(text(), 'Filter By')]")
    private WebElement categorySideBarMenu;

    @FindBy(xpath = "//div[@class='col-md-6 hidden-sm-down total-products']/p")
    private WebElement countConfirmationMessage;

    @FindBy(xpath = "//div[@itemprop='itemListElement']")
    private List<WebElement> foundedInCategoryProductsList;

    @FindBy(xpath = "//ul[@class='category-sub-menu']//a")
    private List<WebElement> subcategoryList;

    @FindBy(xpath = "//li//p")
    private WebElement priceMark;

    @FindBy(xpath = "//a[contains(@class, 'ui-slider-handle ui-state-default ui-corner-all')][last()]")
    private WebElement topBoundSliderHandler;

    @FindBy(xpath = "//div[contains(@id, 'slider-range')]//a[1]")
    private WebElement bottomBoundSliderHandler;

    @FindBy(xpath = "//span[@class='price']")
    private List<WebElement> pricesFilteredProductsList;

    @FindBy(xpath = "//div[@id='_desktop_search_filters_clear_all']/button")
    private WebElement clearFiltersButton;

    public String getCategoryTitle() {
        logger.info("<<<<<<<<<< Category title is: " + categoryTitle.getText());
        return categoryTitle.getText();
    }

    public Boolean isDisplayedCategorySideBarMenu() {
        if (categorySideBarMenu != null) {
            logger.info("<<<<<<<<<< Category side bar menu is displayed");
            return true;
        } else {
            return false;
        }
    }

    public Boolean accordingCountProductsWithConfirmationMessage() {
        if (countConfirmationMessage.getText().contains(String.valueOf(foundedInCategoryProductsList.size()))) {
            logger.info("<<<<<<<<<< Count confirmationMessage is: " + countConfirmationMessage.getText());
            logger.info("<<<<<<<<<< Count of founded products is: " + foundedInCategoryProductsList.size());
            return true;
        } else {
            return false;
        }
    }

    public List<WebElement> getSubcategories() {
        return subcategoryList;
    }

    private void moveTopSliderHandler(int valueTopToStop) {
        int index = priceMark.getText().indexOf("- $") + 3;
        int currentValue = Integer.parseInt(String.format("%.0f", Double.parseDouble(priceMark.getText().substring(index))));
        logger.info("<<<<<<<<<< Current top value is " + currentValue);
        int howMuchStepsNeedDo = valueTopToStop - currentValue;
        logger.info("<<<<<<<<<< Steps needs do TopHandler: " + howMuchStepsNeedDo);

        while (howMuchStepsNeedDo != 0) {
            if (howMuchStepsNeedDo > 0) {
                goRight(howMuchStepsNeedDo, topBoundSliderHandler);
            }
            if (howMuchStepsNeedDo < 0) {
                goLeft(howMuchStepsNeedDo, topBoundSliderHandler);
            }
            currentValue = Integer.parseInt(String.format("%.0f", Double.parseDouble(priceMark.getText().substring(index))));
            howMuchStepsNeedDo = valueTopToStop - currentValue;
        }
    }

    private void moveBottomSliderHandler(int valueBottomStop) {
        int indexStart = priceMark.getText().indexOf("$") + 1;
        int indexEnd = priceMark.getText().indexOf(" - $");
        int currentLowValue = Integer.parseInt(String.format("%.0f", Double.parseDouble(priceMark.getText().substring(indexStart, indexEnd))));
        logger.info("<<<<<<<<<< Current lower value  is " + currentLowValue);
        int howMuchStepsNeedDoLow = valueBottomStop - currentLowValue;
        logger.info("<<<<<<<<<< Steps needs do LowHandler: " + howMuchStepsNeedDoLow);

        while (howMuchStepsNeedDoLow != 0) {
            if (howMuchStepsNeedDoLow > 0) {
                goRight(howMuchStepsNeedDoLow, bottomBoundSliderHandler);
            }
            if (howMuchStepsNeedDoLow < 0) {
                goLeft(howMuchStepsNeedDoLow, bottomBoundSliderHandler);
            }
            currentLowValue = Integer.parseInt(String.format("%.0f", Double.parseDouble(priceMark.getText().substring(indexStart, indexEnd))));
            howMuchStepsNeedDoLow = valueBottomStop - currentLowValue;
        }
    }

    private void goRight(int steps, WebElement webElement) {
        logger.info("<<<<<<<<<< go right ");
        try {
            for (int i = 0; i < steps; i++) {
                webElement.sendKeys(Keys.ARROW_RIGHT);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void goLeft(int steps, WebElement webElement) {
        logger.info("<<<<<<<<<< go left ");
        try {
            for (int i = 0; i < Math.abs(steps); i++) {
                webElement.sendKeys(Keys.ARROW_LEFT);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Boolean isPriceInChosenBounds(int minValue, int maxValue) {
        Boolean result = false;
        for (int i = 0; i < pricesFilteredProductsList.size(); i++) {
            logger.info("<<<<<<<<<< Checking if price is between chosen boundaries:");
            logger.info("<<<<<<<<<< MinValue is " + minValue + " ,MaxValue is " + maxValue);
            logger.info("<<<<<<<<<< Price is " + Integer.parseInt(String.format("%.0f", Double.parseDouble(pricesFilteredProductsList.get(i).getText().substring(1)))));

            if (minValue <= Integer.parseInt(String.format("%.0f", Double.parseDouble(pricesFilteredProductsList.get(i).getText().substring(1)))) &&
                    Integer.parseInt(String.format("%.0f", Double.parseDouble(pricesFilteredProductsList.get(i).getText().substring(1)))) <= maxValue) {
                result = true;
            } else {
                result = false;
            }
        }
        return result;
    }

    public void filterByPrice(int minVal, int maxVal) {
        logger.info("<<<<<<<<<< Filter values: min - " + minVal + " ,max - " + maxVal);
        moveTopSliderHandler(maxVal);
        moveBottomSliderHandler(minVal);
    }

    public void chooseRandomProductInCategory(){
        chooseRandomValueFromList(foundedInCategoryProductsList).click();
    }
}

