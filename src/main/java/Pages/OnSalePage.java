package Pages;

import Base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.util.List;

public class OnSalePage extends BasePage {
    private WebDriver driver;
    private static Logger logger = LoggerFactory.getLogger("OnSalePage.class");

    public OnSalePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//h2")
    private WebElement titleOnSalePage;

    @FindBy(xpath = "//div[@itemprop='itemListElement']")
    private List<WebElement> productsOnSaleList;

//    @FindBy(xpath = "//div[@class='thumbnail-container reviews-loaded']/a")
    @FindBy(xpath = "//h2[@class=\"h3 product-title\"]")
    private WebElement imagesOnSaleList;

    By discountLabelOnProductElement = By.xpath(".//li[@class='product-flag discount']");
    By regularPriceElement = By.xpath(".//span[@class='regular-price']");
    By discountedPriceElement = By.xpath(".//span[@class='price']");

    public String getTitleOnSalePage() {
        return titleOnSalePage.getText();
    }

    public Boolean isDisplayedDiscountLabelOnProduct() {
        Boolean result = false;
        for (int i = 0; i < productsOnSaleList.size(); i++) {
            WebElement discountLabelOnProduct = productsOnSaleList.get(i).findElement(discountLabelOnProductElement);
            if (discountLabelOnProduct != null) {
                logger.info("<<<<<<<<<<<<< DISCOUNT IS " + discountLabelOnProduct.getText());
                result = true;
            } else {
                result = false;
            }
        }
        return result;
    }

    public Boolean isDisplayedTwoPricesOnProduct() {
        Boolean result = false;
        for (int i = 0; i < productsOnSaleList.size(); i++) {
            WebElement regularPrice = productsOnSaleList.get(i).findElement(regularPriceElement);
            WebElement discountedPrice = productsOnSaleList.get(i).findElement(discountedPriceElement);
            if (regularPrice != null && discountedPrice != null) {
                logger.info("<<<<<<<<<<<<< REGULAR PRICE IS " + regularPrice.getText() + ", DISCOUNTED PRICE IS " + discountedPrice.getText());
                result = true;
            } else {
                result = false;
            }
        }
        logger.info("<<<<<<<<<<<<< Checked prices on On Sale Page: discounted and regular price are displayed");
        return result;
    }

    public Boolean isDiscountedPriceLowerThanRegularOnDiscountSize() {
        Boolean result = false;
        for (int i = 0; i < productsOnSaleList.size(); i++) {
            Double regularPrice = Double.parseDouble(productsOnSaleList.get(i).findElement(regularPriceElement).getText().substring(1));
            Double discountedPrice = Double.parseDouble(productsOnSaleList.get(i).findElement(discountedPriceElement).getText().substring(1));
            logger.info("<<<<<<<<<<<<< REGULAR PRICE IS " + regularPrice + ", DISCOUNTED PRICE IS " + discountedPrice);
            if (Double.parseDouble(String.format("%.2f", (100 * discountedPrice / regularPrice))) == 100.00 - Double.parseDouble(System.getProperty("discountSize"))) {
                result = true;
                logger.info("<<<<<<<<<<<<< Compared prices on On Sale Page: discounted price is on discount size lower than regular");
            } else {
                result = false;
            }
        }
        logger.info("<<<<<<<<<<<<< Compared prices on On Sale Page");
        return result;
    }

    public void clickRandomValueFromListOfProductsOnSale() {
        chooseRandomValueFromList(productsOnSaleList).click();
    }
}