package Pages;

import Base.BasePage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class OrderDataPage extends BasePage {
    private WebDriver driver;
    private static Logger logger = LoggerFactory.getLogger("OrderDataPage.class");

    public OrderDataPage(WebDriver driver) {
        super(driver);
        logger.info("########## OrderDataPage is created");
    }

    @FindBy(xpath = "//input[@name='address1']")
    private WebElement addressField;

    @FindBy(xpath = "//input[@name='city']")
    private WebElement cityField;

    @FindBy(xpath = "//select[@name='id_country']/option")
    private List<WebElement> countries;

    @FindBy(xpath = "//button[@name='confirm-addresses']")
    private WebElement continueButtonAddressesSection;

    @FindBy(xpath = "//input[@name='postcode']")
    private WebElement postalCodeField;

    @FindBy(xpath = "name='id_country'")
    private WebElement countryField;

    @FindBy(xpath = "//button[@name='confirmDeliveryOption']")
    private WebElement continueButtonDeliverySection;

    @FindBy(xpath = "//input[contains(@id, 'delivery_option')]")
    private List<WebElement> deliveryOptionsList;

    @FindBy(xpath = "//input[@data-module-name='ps_wirepayment']")
    private WebElement payByBankWireRadioButton;

    @FindBy(xpath = "//a[@id='cta-terms-and-conditions-0']")
    private WebElement termsOfServiceLink;

    @FindBy(xpath = "//input[@id='conditions_to_approve[terms-and-conditions]']")
    private WebElement acceptTermsOfServiceCheckBox;

    @FindBy(xpath = "//div[@id='payment-confirmation']//button")
    private WebElement placeOrderButton;

    private void selectCountry() {
        for (WebElement country : countries) {
            if (country.getText().equals(System.getProperty("Country"))) {
                country.click();
                logger.info("<<<<<<<<<< Country selected: " + System.getProperty("Country"));
            }
        }
    }

    private void fillAddressesSectionFields() {
        clearFieldAndSendKeys(addressField, System.getProperty("Address"));
        clearFieldAndSendKeys(cityField, System.getProperty("City"));
        clearFieldAndSendKeys(postalCodeField, System.getProperty("PostalCode"));
        selectCountry();
    }

    private void clickContinueButtonAddressesSection() {
        wait.until(ExpectedConditions.elementToBeClickable(continueButtonAddressesSection));
        continueButtonAddressesSection.submit();
        logger.info("<<<<<<<<<< Submit CONTINUE button in Addresses section");
        continueButtonAddressesSection.click();
        logger.info("<<<<<<<<<< Click on CONTINUE button in Addresses section");
    }

    public OrderDataPage fillAddressesSection() {
        fillAddressesSectionFields();
        clickContinueButtonAddressesSection();
        return this;
    }

    public OrderDataPage fillDeliverySection() {
        deliveryOptionsList.get(deliveryOptionsList.size() - 1).click();
        continueButtonDeliverySection.click();
        logger.info("<<<<<<<<<< Click on CONTINUE button in Delivery section");
    return this;}

    public OrderDataPage selectPaymentMethod() {
        payByBankWireRadioButton.click();
        logger.info("<<<<<<<<<< Chosen payment method");
        return this;}

    public OrderDataPage openTermsOfServiceLink() {
        termsOfServiceLink.click();
        logger.info("<<<<<<<<<< Click on TermsOfServiceLink");
        switchToLastOpenedWindow();
        return this;}

    public OrderDataPage acceptTermOfService() {
        acceptTermsOfServiceCheckBox.click();
    return this;}

    public void clickPlaceOrderButton() {
        placeOrderButton.click();
    }
}
