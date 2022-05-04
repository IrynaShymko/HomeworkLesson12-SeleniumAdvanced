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

public class OrderDetailsPage extends BasePage {
    private WebDriver driver;
    private static Logger logger = LoggerFactory.getLogger("OrderDetailsPage.class");

    public OrderDetailsPage(WebDriver driver) {
        super(driver);
        logger.info("########## OrderDetailsPage is created");
    }

    @FindBy(xpath = "//table[@id='order-products']//tbody//tr")
    private List<WebElement> rowsInDetailPageTable;

    @FindBy(xpath = "//article[@id='delivery-address']/address")
    private WebElement deliveryAddress;

    @FindBy(xpath = "//article[@id='invoice-address']/address")
    private WebElement invoiceAddress;

    private List<Product> collectProducts() {
        List<Product> products = new ArrayList<>();
        for (WebElement productRow : rowsInDetailPageTable) {
            WebElement productName = productRow.findElement(By.xpath(".//td[1]"));
            WebElement productPrice = productRow.findElement(By.xpath(".//td[3]"));
            WebElement productQuantity = productRow.findElement(By.xpath(".//td[2]"));

            String name = productName.getText();
            Double price = Double.parseDouble(productPrice.getText().substring(1));
            int quantity = Integer.parseInt(productQuantity.getText());
            Product product = new Product(name, price, quantity);
            products.add(product);
        }
        return products;
    }


    public Boolean isProductDataInOrderDetailsTheSameInProductBox(ProductBox productBox) {
        logger.info("<<<<<<<<<< Checking matches in Order Details and ProductBox");
        boolean result = false;
        List<Product> productsInDetails = collectProducts();
        for (int i = 0; i < productsInDetails.size(); i++) {
            for (int j = 0; j < productBox.getProducts().size(); j++) {
                if (productsInDetails.get(i).getProductName().contains(productBox.getProducts().get(j).getProductName())
                        && String.valueOf(productsInDetails.get(i).getPrice()).equals(String.valueOf(productBox.getProducts().get(j).getPrice()))
                        && productsInDetails.get(i).getQuantity() == productBox.getProducts().get(j).getQuantity()) {
                    result = true;
                    logger.info("<<<<<<<<<< There is match:: Product Name is: " + productsInDetails.get(i).getProductName());
                } else result = false;
            }
        }
        return result;
    }


    private Boolean isAddressOnDetailsCorrect(WebElement webElement) {
        if (webElement.getText().contains(System.getProperty("Address"))
                && webElement.getText().contains(System.getProperty("City"))
                && webElement.getText().contains(System.getProperty("Country"))
                && webElement.getText().contains(System.getProperty("PostalCode"))) {
            return true;
        } else return false;
    }

    public Boolean isDeliveryAddressOnDetailsCorrect() {
        logger.info("<<<<<<<<<< Checking delivery address");
        return isAddressOnDetailsCorrect(deliveryAddress);
    }

    public Boolean isInvoiceAddressOnDetailsCorrect() {
        logger.info("<<<<<<<<<< Checking invoice address");
        return isAddressOnDetailsCorrect(invoiceAddress);
    }
}
