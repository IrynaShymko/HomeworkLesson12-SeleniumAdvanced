package Pages;

import Base.BasePage;
import Helpers.OrderDataInfo;
import Helpers.ProductBox;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class OrderHistoryPage extends BasePage {
    private WebDriver driver;
    private static Logger logger = LoggerFactory.getLogger("OrderHistoryPage.class");

    public OrderHistoryPage(WebDriver driver) {
        super(driver);
        logger.info("########## OrderHistoryPage is created");
    }

    @FindBy(xpath = "//tbody//tr")
    private List<WebElement> rowsInOrderHistoryList;

    public Boolean isCommonOrderInfoTheSameInProductBox(OrderDataInfo orderDataInfo, ProductBox productBox) {
        logger.info("<<<<<<<<<< Start checking CommonOrderInfo");
        Boolean result = false;
        for (WebElement rowInOrderHistoryList : rowsInOrderHistoryList) {
            String referenceNum = rowInOrderHistoryList.findElement(By.xpath(".//th")).getText();
            String date = rowInOrderHistoryList.findElement(By.xpath(".//td[1]")).getText();
            String totalCost = rowInOrderHistoryList.findElement(By.xpath(".//td[2]")).getText().substring(1);
            String payment = rowInOrderHistoryList.findElement(By.xpath(".//td[3]")).getText();
            String status = rowInOrderHistoryList.findElement(By.xpath(".//td[4]/span")).getText();
            logger.info("<<<<<<<<<< referenceNum "+referenceNum);
            logger.info("<<<<<<<<<< SavedReference "+orderDataInfo.getOrderReferenceNumber());
            logger.info("<<<<<<<<<< date " + date);
            logger.info("<<<<<<<<<< dateToday " + getDateToday());
            logger.info("<<<<<<<<<< totalCost " + totalCost);
            logger.info("<<<<<<<<<< totalCostInBox " + String.format("%.2f", productBox.getTotalOrderCostInBox(productBox.getProducts())));

            if ((orderDataInfo.getOrderReferenceNumber().contains(referenceNum))
                    && date.contains(getDateToday())
                    && totalCost.contains(String.valueOf(Double.parseDouble(String.format("%.2f", productBox.getTotalOrderCostInBox(productBox.getProducts())))))
                    && payment.contains(System.getProperty("SynonymicNameBankWirePaymentMethod"))
                    && status.contains(System.getProperty("PaymentStatus"))) {
                result = true;
            }
        }
        logger.info("<<<<<<<<<< Checked CommonOrderInfo");
        return result;
    }

    public void clickOnDetailsLink(OrderDataInfo orderDataInfo){
        for (WebElement rowInOrderHistoryList : rowsInOrderHistoryList) {
            String referenceNum = rowInOrderHistoryList.findElement(By.xpath(".//th")).getText();
            WebElement detailsLink = rowInOrderHistoryList.findElement(By.xpath(".//a[contains(text(), 'Details')]"));
            if(orderDataInfo.getOrderReferenceNumber().contains(referenceNum)){
            detailsLink.click();}
        }
    }
}