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

public class ModalCartPage extends BasePage {
    private WebDriver driver;
    private static Logger logger = LoggerFactory.getLogger("ModalCartPage.class");

    public ModalCartPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//h6")
    private WebElement productNameOnModalCartWindow;

    @FindBy(xpath = "//p[@class='product-price']")
    private WebElement productPriceOnModalCartWindow;

    @FindBy(xpath = "//span[@class='product-quantity']")
    private WebElement productQuantityOnModalCartWindow;

    @FindBy(xpath = "//p[@class='cart-products-count']")
    private WebElement totalQuantityMessage;

    @FindBy(xpath = "//span[@class='subtotal value']")
    private WebElement totalSumWithoutShipping;

    @FindBy(xpath = "//button[contains(text(), 'Continue shopping')]")
    private WebElement continueShoppingButton;


    public String getProductNameFromModalCartWindow() {
        wait.until(ExpectedConditions.visibilityOf(productNameOnModalCartWindow));
        String name = productNameOnModalCartWindow.getText();
        logger.info("<<<<<<<<<< Product Name on Modal Cart window is: " + name);
        return name;
    }

    public Double getProductPriceFromModalCartWindow() {
        Double price = Double.parseDouble(String.format("%.2f", Double.parseDouble(productPriceOnModalCartWindow.getText().substring(1))));
        logger.info("<<<<<<<<<< Price on Modal Cart window is: " + price);
        return price;
    }

    public int getProductQuantityFromModalCartWindow() {
        int quantity = Integer.parseInt(productQuantityOnModalCartWindow.getText().substring(10));
        logger.info("<<<<<<<<<< Product quantity on Modal Cart window is: " + quantity);
        return quantity;
    }

    public Boolean isProductInfoInCartTheSameInProductBox(ProductBox productBox) {
        Product productInCart = new Product(getProductNameFromModalCartWindow(),
                getProductPriceFromModalCartWindow(),
                getProductQuantityFromModalCartWindow());
        Product productInBox = productBox.getProducts().get(productBox.getProducts().size() - 1);
        logger.info("<<<<<<<<<< Product name of last product in box: " + productInBox.getProductName());
        logger.info("<<<<<<<<<< Product Price of last product in box: " + productInBox.getPrice());
        logger.info("<<<<<<<<<< Product quantity of last product in box: " + productInBox.getQuantity());
        if (productInCart.areEqual(productBox)) {
            return true;
        } else return false;
    }

    public Boolean isCommonInfoInCartTheSameInProductBox(ProductBox productBox) {
        int indexEnd = totalQuantityMessage.getText().indexOf(" items");
        int quantityOfProductOnModalCartWindow = Integer.parseInt(totalQuantityMessage.getText().substring(10, indexEnd));
        Double sumOfAllProductsOnModalCartWindow = Double.parseDouble(String.format("%.2f", Double.parseDouble(totalSumWithoutShipping.getText().substring(1))));
        int quantityInBox = productBox.getTotalQuantityProductsInBox();
        Double sumOfAllProductsInBox = Double.parseDouble(String.format("%.2f",productBox.getTotalOrderCost()));
        logger.info("<<<<<<<<<< quantityOfProductOnModalCartWindow: " + quantityOfProductOnModalCartWindow);
        logger.info("<<<<<<<<<< quantityInBox: " + quantityInBox);
        logger.info("<<<<<<<<<< sumOfAllProductsOnModalCartWindow: " + sumOfAllProductsOnModalCartWindow);
        logger.info("<<<<<<<<<< sumOfAllProductsInBox: " + sumOfAllProductsInBox);
        if(quantityOfProductOnModalCartWindow==quantityInBox
                && String.valueOf(sumOfAllProductsOnModalCartWindow).equals(String.valueOf(sumOfAllProductsInBox))){
                    return true;
        } else {return false; }
    }

    public void clickContinueShoppingButton(){
        hoverAndClick(continueShoppingButton);
        switchToLastOpenedWindow();
    }
}
