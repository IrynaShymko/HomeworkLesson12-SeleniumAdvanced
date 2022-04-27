package Pages;

import Base.BasePage;

import Helpers.ProductBox;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class BasketPage extends BasePage {
    private WebDriver driver;
    private static Logger logger = LoggerFactory.getLogger("BasketPage.class");

    public BasketPage(WebDriver driver) {
        super(driver);
        logger.info("########## BasketPage is created");
    }

    @FindBy(xpath = "//div[@class='product-line-grid']")
    private List<WebElement> productsInCart;

    @FindBy(xpath = "//div[@class='product-line-info']/a")
    private List<WebElement> productNamesInBasket;

    @FindBy(xpath = "//span[@class='price']")
    private List<WebElement> productPricesInBasket;

    @FindBy(xpath = "//input[@name='product-quantity-spin']")
    private List<WebElement> productQuantitiesInBasket;

    @FindBy(xpath = "//button[contains(@class, 'touchspin-up')]")
    private List<WebElement> listArrowUpButtons;

    @FindBy(xpath = "//button[contains(@class, 'touchspin-down')]")
    private List<WebElement> listArrowDownButtons;

    @FindBy(xpath = "//div[@class='cart-summary-line cart-total']//span[@class='value']")
    private WebElement totalCostInCart;

    @FindBy(xpath = "//h1")
    private WebElement title;

    @FindBy(xpath = "//a[@class='remove-from-cart']")
    private List<WebElement> trashIconsList;

    @FindBy(xpath = "//div[@class='checkout cart-detailed-actions card-block']//a")
    private WebElement proceedToCheckoutButtonInCart;

    public int getNumberProductsInCart() {
        return productsInCart.size();
    }

    public Boolean isProductDataInCartTheSameInProductBox(ProductBox productBox) {
        boolean result = false;
        for (int i = 0; i < productsInCart.size(); i++) {
            for (int j = 0; j < productBox.getProducts().size(); j++) {
                if (productNamesInBasket.get(i).getText().equals(productBox.getProducts().get(j).getProductName())) {
                    Double priceInCart = Double.parseDouble(String.format("%.2f", Double.parseDouble(productPricesInBasket.get(i).getText().substring(1))));
                    if (String.valueOf(priceInCart).equals(String.valueOf(productBox.getProducts().get(j).getPrice()))) {
                        if (Integer.parseInt(productQuantitiesInBasket.get(i).getAttribute("value")) == productBox.getProducts().get(j).getQuantity()) {

                            result = true;
                            break;
                        }
                    }
                } else result = false;
            }
        }
        logger.info("<<<<<<<<<< Matches in Basket and ProductBox checked");
        return result;
    }

    public Boolean isTotalCostTheSameInCartThatInProductBox(ProductBox productBox) {
        try {
            Thread.sleep(2000);
            logger.info("<<<<<<<<<< Check Total order cost");
            String totalCostInBox = String.valueOf(Double.parseDouble(String.format("%.2f", productBox.getTotalOrderCostInBox(productBox.getProducts()))));
            String totalCostInBasket = String.valueOf(Double.parseDouble(String.format("%.2f", Double.parseDouble(totalCostInCart.getText().substring(1)))));
            logger.info("<<<<<<<<<< TotalCostInBox is " + totalCostInBox);
            logger.info("<<<<<<<<<< TotalCostInBasket is " + totalCostInBasket);
            if (totalCostInBox.equals(totalCostInBasket)) {
                return true;
            } else return false;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }


    private void changeQuantityFirstProductInCart(String value) {

        productQuantitiesInBasket.get(0).click();
        productQuantitiesInBasket.get(0).sendKeys(Keys.BACK_SPACE);
        clearFieldAndSendKeys(productQuantitiesInBasket.get(0), value);
        hoverAndDoubleClick(title);
    }

    public void changeQuantityOfFirstProduct(ProductBox productBox, String value) {
        changeQuantityFirstProductInCart(value);
        String nameOfFirstProduct = productNamesInBasket.get(0).getText();
        for (int i = 0; i < productBox.getProducts().size(); i++) {
            if (productBox.getProducts().get(i).getProductName().equals(nameOfFirstProduct)) {
                productBox.getProducts().get(i).setQuantity(Integer.parseInt(value));
            }
        }
    }

    public void clickOnArrowUpButtonFirstProduct() {
        hoverAndClick(listArrowUpButtons.get(0));
    }

    public void clickOnArrowDownButtonFirstProduct() {
        hoverAndClick(listArrowDownButtons.get(0));
    }

    public Boolean increasedQuantityAfterClickingArrowUpButton(ProductBox productBox) {
        logger.info("<<<<<<<<<< Checking increasing of quantity after clicking ArrowUp button");
        int startValue = Integer.parseInt(productQuantitiesInBasket.get(0).getAttribute("value"));
        clickOnArrowUpButtonFirstProduct();
        int endValue = Integer.parseInt(productQuantitiesInBasket.get(0).getAttribute("value"));
        logger.info("<<<<<<<<<< StartValue is " + startValue);
        logger.info("<<<<<<<<<< EndValue is " + endValue);
        if (startValue < endValue) {
            changeQuantityOfFirstProductAfterArrowButton(productBox, String.valueOf(endValue));
            return true;
        } else return false;
    }

    public Boolean decreasedQuantityAfterClickingArrowDownButton(ProductBox productBox) {
        logger.info("<<<<<<<<<< Checking decreasing of quantity after clicking ArrowDown button");
        int startValue = Integer.parseInt(productQuantitiesInBasket.get(0).getAttribute("value"));
        clickOnArrowDownButtonFirstProduct();
        int endValue = Integer.parseInt(productQuantitiesInBasket.get(0).getAttribute("value"));
        logger.info("<<<<<<<<<< StartValue is " + startValue);
        logger.info("<<<<<<<<<< EndValue is " + endValue);
        if (startValue > endValue) {
            changeQuantityOfFirstProductAfterArrowButton(productBox, String.valueOf(endValue));
            return true;
        } else return false;

    }

    public void changeQuantityOfFirstProductAfterArrowButton(ProductBox productBox, String value) {
        String nameOfFirstProduct = productNamesInBasket.get(0).getText();
        for (int i = 0; i < productBox.getProducts().size(); i++) {
            if (productBox.getProducts().get(i).getProductName().equals(nameOfFirstProduct)) {
                productBox.getProducts().get(i).setQuantity(Integer.parseInt(value));
            }
        }
        logger.info("<<<<<<<<<< Changed quantity of product in Box after ArrowButton is clicked");
    }

    private void deleteProductFromCartByTrashIcon(ProductBox productBox) {
        String nameOfDeletedProduct = productNamesInBasket.get(0).getText();
        int quantityOfDeletedProduct = Integer.parseInt(productQuantitiesInBasket.get(0).getAttribute("value"));
        hoverAndClick(trashIconsList.get(0));
        removeProductFromProductBox(productBox,nameOfDeletedProduct,quantityOfDeletedProduct);
        logger.info("<<<<<<<<<< Deleted product in Cart");
    }

    private void removeProductFromProductBox(ProductBox productBox,  String nameOfDeletedProduct, int quantityOfDeletedProduct) {
        for (int i = 0; i < productBox.getProducts().size(); i++) {
            int quantityOfProductInBox = productBox.getProducts().get(i).getQuantity();
            if (productBox.getProducts().get(i).getProductName().equals(nameOfDeletedProduct) && quantityOfProductInBox==quantityOfDeletedProduct) {
                productBox.getProducts().remove(productBox.getProducts().get(i));
            }
        }
        logger.info("<<<<<<<<<< Deleted product in Box");
    }

    public Boolean isCorrectTotalCostAfterRemovingProductsOneByOne(ProductBox productBox) {
        logger.info("<<<<<<<<<< Removing products one by one and checking Total cost");
        Boolean result = false;
        int timesToRepeatDeleting =trashIconsList.size();
        for (int i = 0; i < timesToRepeatDeleting; i++) {
            deleteProductFromCartByTrashIcon(productBox);
            result = isTotalCostTheSameInCartThatInProductBox(productBox);
            logger.info("<<<<<<<<<< Is total cost the same that in Product Box: " + result);
        }
        return result;
    }

    public void clickOnProceedToCheckoutButtonInCart(){
        proceedToCheckoutButtonInCart.click();
        logger.info("<<<<<<<<<< Click on Proceed to checkout Button in cart");
    }
}


