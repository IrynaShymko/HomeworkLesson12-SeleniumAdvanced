package Pages;

import Base.BasePage;
import Helpers.OrderDataInfo;
import Helpers.Product;
import Helpers.ProductBox;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class OrderConfirmationPage extends BasePage {
    private WebDriver driver;
    private static Logger logger = LoggerFactory.getLogger("OrderConfirmationPage.class");

    public OrderConfirmationPage(WebDriver driver) {
        super(driver);
        logger.info("########## OrderConfirmationPage is created");
    }

    @FindBy(xpath = "//div[@class='order-line row']")
    private List<WebElement> productRowsList;

    @FindBy(xpath = "//li[contains(text(), 'Payment method')]")
    private WebElement paymentMethod;

    @FindBy(xpath = "//li[contains(text(), 'Shipping method')]")
    private WebElement shippingMethod;

    @FindBy(xpath = "//li[contains(text(), 'Order reference')]")
    private WebElement orderReferenceNumber;


    private List<Product> collectProductsFromConfirmationPgeToList() {
        List<Product> products = new ArrayList<>();
        for (WebElement productRow : productRowsList) {
            WebElement productName = productRow.findElement(By.xpath(".//div[@class='col-sm-4 col-xs-9 details']/span"));
            WebElement productPrice = productRow.findElement(By.xpath(".//div[@class='col-xs-4 text-sm-center text-xs-left']"));
            WebElement productQuantity = productRow.findElement(By.xpath(".//div[@class='col-xs-4 text-sm-center']"));

            String name = productName.getText();
            Double price = Double.parseDouble(productPrice.getText().substring(1));
            int quantity = Integer.parseInt(productQuantity.getText());
            Product product = new Product(name, price, quantity);
            products.add(product);
        }
        return products;
    }


    public Boolean isProductDataInOrderConfirmationTheSameInProductBox(ProductBox productBox) {
        logger.info("<<<<<<<<<< Checking matches in Order Confirmations and ProductBox");
        boolean result = false;
        List<Product> productsInConfirmation = collectProductsFromConfirmationPgeToList();
        for (int i = 0; i < productsInConfirmation.size(); i++) {
            for (int j = 0; j < productBox.getProducts().size(); j++) {
                if (productsInConfirmation.get(i).getProductName().contains(productBox.getProducts().get(j).getProductName())
                        && String.valueOf(productsInConfirmation.get(i).getPrice()).equals(String.valueOf(productBox.getProducts().get(j).getPrice()))
                        && productsInConfirmation.get(i).getQuantity() == productBox.getProducts().get(j).getQuantity()) {
                    result = true;
                    logger.info("<<<<<<<<<< There is match:: Product Name is: " + productsInConfirmation.get(i).getProductName());
                } else result = false;
            }
        }
        return result;
    }

    public Boolean isPaymentMethodTheSameWasSelected() {
        logger.info("<<<<<<<<<< Checking BankPaymentMethod in Order Confirmations");
        String paymentMethodInConfirmation = paymentMethod.getText().substring(16);
        if (paymentMethodInConfirmation.contains(System.getProperty("SynonymicNameBankWirePaymentMethod"))) {
            return true;
        } else return false;
    }

    public Boolean isShippingMethodTheSameWasSelected() {
        logger.info("<<<<<<<<<< Checking ShippingMethod in Order Confirmations");
        String shippingMethodInConfirmation = shippingMethod.getText().substring(16);
        if (shippingMethodInConfirmation.contains(System.getProperty("DeliveryOptionName"))) {
            return true;
        } else return false;
    }

    public void saveOrderReferenceNumber(OrderDataInfo orderDataInfo){
        orderDataInfo.setOrderReferenceNumber(orderReferenceNumber.getText().substring(16));
        logger.info("<<<<<<<<<< Order Reference Number is saved");
    }
}