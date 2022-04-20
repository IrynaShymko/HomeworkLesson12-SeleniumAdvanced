package Pages;

import Base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ProductDetailsPage extends BasePage {
    private WebDriver driver;
    private static Logger logger = LoggerFactory.getLogger("ProductDetailsPage.class");

    public ProductDetailsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//span[@class='discount discount-percentage']")
    private WebElement discountLabelOnProductDetailsPage;

    @FindBy(xpath = "//span[@class='regular-price']")
    private WebElement regularPriceOnProductDetails;

    @FindBy(xpath = "//span[@itemprop='price']")
    private WebElement discountedPriceOnProductDetails;

    public Boolean hasProductPageDiscountLabel(){
        Boolean result;
        wait.until(ExpectedConditions.visibilityOf(discountLabelOnProductDetailsPage));
        if(discountLabelOnProductDetailsPage !=null){
            logger.info("<<<<<<<<<<<<< Discount Label Text is: "+ discountLabelOnProductDetailsPage.getText());
            result= true;}
        else {result= false;}
        return result;
    }

    public Boolean isDisplayedTwoPricesOnProduct() {
        Boolean result=false;
            if (regularPriceOnProductDetails != null && discountedPriceOnProductDetails !=null) {
                logger.info("<<<<<<<<<<<<< REGULAR PRICE IS "+regularPriceOnProductDetails.getText() + ", DISCOUNTED PRICE IS " +discountedPriceOnProductDetails.getText());
                result = true;
            } else {
                result = false;
            }
        logger.info("<<<<<<<<<<<<< Checked prices on product details Page: discounted and regular price are displayed");
        return result;
    }

    public Boolean OnProductDetailsIsDiscountedPriceLowerThanRegularOnDiscountSize() {
        Boolean result = false;
            Double regularPrice = Double.parseDouble(regularPriceOnProductDetails.getText().substring(1));
            Double discountedPrice = Double.parseDouble(discountedPriceOnProductDetails.getText().substring(1));
            logger.info("<<<<<<<<<<<<< REGULAR PRICE IS " + regularPrice + ", DISCOUNTED PRICE IS " + discountedPrice);
            if (Double.parseDouble(String.format("%.2f", (100 * discountedPrice / regularPrice))) == 100.00 - Double.parseDouble(System.getProperty("discountSize"))) {
                result = true;
                logger.info("<<<<<<<<<<<<< Compared prices on product details Page: discounted price is on discount size lower than regular");
            } else {
                result = false;
        }
        logger.info("<<<<<<<<<<<<< Compared prices on product details Page");
        return result;
    }
}
