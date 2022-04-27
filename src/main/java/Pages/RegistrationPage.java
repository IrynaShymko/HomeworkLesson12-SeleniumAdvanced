package Pages;

import Base.BasePage;
import Helpers.User;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RegistrationPage extends BasePage {
    private WebDriver driver;
    private static Logger logger = LoggerFactory.getLogger("RegistrationPage.class");

    public RegistrationPage(WebDriver driver) {
        super(driver);
        logger.info("########## RegistrationPage is created");
    }
    @FindBy(xpath = "//input[@name='firstname']")
    private WebElement firstNameField;

    @FindBy(xpath = "//input[@name='lastname']")
    private WebElement lastNameField;

    @FindBy(xpath = "//form[@id='customer-form']//input[@name='email']")
    private WebElement emailField;

    @FindBy(xpath = "//input[@name='password']")
    private WebElement passwordField;

    @FindBy(xpath = "//input[@name='customer_privacy']")
    private WebElement checkBoxCustomerDataPrivacy;

    @FindBy(xpath = "//input[@name='psgdpr']")
    private WebElement checkBoxAcceptRegulamin;

    @FindBy(xpath = "//button[@data-link-action='save-customer'] ")
    private WebElement saveRegistrationDataButton;

    public RegistrationPage fillRegistrationForm(User user){
        wait.until(ExpectedConditions.visibilityOf(firstNameField));
        clearFieldAndSendKeys(firstNameField, user.getFirstName());
        clearFieldAndSendKeys(lastNameField, user.getLastName());
        clearFieldAndSendKeys(emailField, user.getEmail());
        clearFieldAndSendKeys(passwordField, user.getPassword());
        checkBoxCustomerDataPrivacy.click();
        checkBoxAcceptRegulamin.click();
        logger.info("<<<<<<<<<< Registration form is filled");
        return this;
    }

    public void submitRegistrationForm(){
        saveRegistrationDataButton.click();
        logger.info("<<<<<<<<<< Registration form is submitted");
    }

}
