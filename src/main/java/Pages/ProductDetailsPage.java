package Pages;

import Base.BasePage;
import Helpers.Product;
import Helpers.ProductBox;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class ProductDetailsPage extends BasePage {
    private WebDriver driver;
    private static Logger logger = LoggerFactory.getLogger("ProductDetailsPage.class");

    public ProductDetailsPage(WebDriver driver) {
        super(driver);
        logger.info("########## ProductDetailsPage is created");
    }

    @FindBy(xpath = "//h1")
    private WebElement productNameOnProductDetailPage;

    @FindBy(xpath = "//span[@class='discount discount-percentage']")
    private WebElement discountLabelOnProductDetailsPage;

    @FindBy(xpath = "//span[@itemprop='price']")
    private WebElement regularPriceOnProductDetails;

    @FindBy(xpath = "//button[@data-button-action='add-to-cart']")
    private WebElement addToCartButton;

    @FindBy(xpath = "//span[@itemprop='price']")
    private WebElement actualPriceOnProductDetails;

    @FindBy(xpath = "//input[@id='quantity_wanted']")
    private WebElement quantityOfProduct;

    @FindBy(xpath = "//textarea[@class='product-message']")
    private WebElement productMessageField;

    @FindBy(xpath = "//button[@name='submitCustomizedData']")
    private WebElement saveCustomizationMessageButton;

    public Boolean hasProductPageDiscountLabel() {
        Boolean result;
        wait.until(ExpectedConditions.visibilityOf(discountLabelOnProductDetailsPage));
        if (discountLabelOnProductDetailsPage != null) {
            logger.info("<<<<<<<<<< Discount Label Text is: " + discountLabelOnProductDetailsPage.getText());
            result = true;
        } else {
            result = false;
        }
        return result;
    }

    public Boolean isDisplayedTwoPricesOnProduct() {
        Boolean result = false;
        if (regularPriceOnProductDetails != null && actualPriceOnProductDetails != null) {
            logger.info("<<<<<<<<<< REGULAR PRICE IS " + regularPriceOnProductDetails.getText() + ", DISCOUNTED PRICE IS " + actualPriceOnProductDetails.getText());
            result = true;
        } else {
            result = false;
        }
        logger.info("<<<<<<<<<< Checked prices on product details Page: discounted and regular price are displayed");
        return result;
    }

    public Boolean OnProductDetailsIsDiscountedPriceLowerThanRegularOnDiscountSize() {
        Boolean result = false;
        Double regularPrice = Double.parseDouble(regularPriceOnProductDetails.getText().substring(1));
        Double discountedPrice = Double.parseDouble(actualPriceOnProductDetails.getText().substring(1));
        logger.info("<<<<<<<<<<<<< REGULAR PRICE IS " + regularPrice + ", DISCOUNTED PRICE IS " + discountedPrice);
        if (Double.parseDouble(String.format("%.2f", (100 * discountedPrice / regularPrice))) == 100.00 - Double.parseDouble(System.getProperty("discountSize"))) {
            result = true;
            logger.info("<<<<<<<<<< Compared prices on product details Page: discounted price is on discount size lower than regular");
        } else {
            result = false;
        }
        logger.info("<<<<<<<<<< Compared prices on product details Page");
        return result;
    }

    public void chooseQuantityOfProduct(int minimumValue, int maximumValue) {
        String productTitle = productNameOnProductDetailPage.getText();
        if(productTitle.equals(System.getProperty("productNameWithBlockedCartButton"))){
            clearFieldAndSendKeys(productMessageField, System.getProperty("customizationMessage")+getRandomValueFromChosenBoundaries(1,100));
            hoverAndClick(saveCustomizationMessageButton);
        }
        clearFieldAndSendKeys(quantityOfProduct, String.valueOf(getRandomValueFromChosenBoundaries(minimumValue, maximumValue)));
    }

    public String getProductNameFromProductDetailsPage() {
        logger.info("<<<<<<<<<< Product name is " + productNameOnProductDetailPage.getText());
        return productNameOnProductDetailPage.getText();
    }

    public Double getProductPriceFromProductDetailsPage() {
        Double price = Double.parseDouble(regularPriceOnProductDetails.getText().substring(1));
        logger.info("<<<<<<<<<< Price is " + price);
        return price;
    }

    public int getQuantityFromProductDetailsPage() {
        int quantity = Integer.parseInt(quantityOfProduct.getAttribute("value"));
        logger.info("<<<<<<<<<< Quantity is " + quantity);
        return quantity;
    }

    public void addProductToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
        hoverAndClick(addToCartButton);
        switchToLastOpenedWindow();
    }

    public Product buildProduct() {
        Product product = new Product(getProductNameFromProductDetailsPage(),
                getProductPriceFromProductDetailsPage(),
                getQuantityFromProductDetailsPage());
        logger.info("<<<<<<<<<< Built product");
        return product;
    }

    public void changeProductBoxContent(ProductBox productBox) {
        Product productCreated = buildProduct();
        if (productBox.getProducts().size() > 0) {
            for (int i = 0; i < productBox.getProducts().size(); i++) {
                if (productBox.getProducts().get(i).isTheSameProduct(productCreated)&& !productBox.getProducts().get(i).getProductName().equals(System.getProperty("productNameWithBlockedCartButton"))) {
                    productBox.getProducts().get(i)
                            .setQuantity((productBox.getProducts().get(i).getQuantity()) + productCreated.getQuantity());
                    logger.info("<<<<<<<<<< QUANTITY OF PRODUCT UPDATED");
                }
            }

            for (int i = 0; i < productBox.getProducts().size(); i++) {
                if (productBox.getProducts().get(i).getProductName().equals(System.getProperty("productNameWithBlockedCartButton"))) {
                    productBox.getProducts().add(productCreated);
                    logger.info("<<<<<<<<<< PRODUCT ADDING in non empty list");
                }
            }

            List<String> names = new ArrayList<>();
            for (int i = 0; i < productBox.getProducts().size(); i++) {
                names.add(productBox.getProducts().get(i).getProductName());
            }
            if (!names.contains(productCreated.getProductName()) ||productCreated.getProductName().equals(System.getProperty("productNameWithBlockedCartButton"))) {
                productBox.getProducts().add(productCreated);
                logger.info("<<<<<<<<<< PRODUCT ADDING in non empty list");
            }
        }
        if (productBox.getProducts().size() == 0) {
            logger.info("<<<<<<<<<< PRODUCT ADDING in Empty list");
            productBox.getProducts().add(productCreated);
        }
    }
}
