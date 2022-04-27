package Pages;

import Base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TermsOfServiceModalPage extends BasePage {
    private WebDriver driver;
    private static Logger logger = LoggerFactory.getLogger("TermsOfServiceModalPage.class");

    public TermsOfServiceModalPage(WebDriver driver) {
        super(driver);
        logger.info("########## TermsOfServiceModalPage is created");
    }

    @FindBy(xpath = "//div[@class='js-modal-content']//p")
    private WebElement contentOfModal;

    @FindBy(xpath = "//div[@class='modal fade in']//button[@data-dismiss='modal']")
    private WebElement closeModalButton;

    public Boolean isContentOfTermsOfServiceNonEmpty(){
        logger.info("<<<<<<<<<< Checking if Content of terms of service is nonEmpty");
        if(!contentOfModal.getText().equals(null)){
            return true;
        }else return false;
    }

    public void closeModalTermsOfService(){
        closeModalButton.click();
        logger.info("<<<<<<<<<< Closed modal Terms of use");
        switchToLastOpenedWindow();
    }
}
