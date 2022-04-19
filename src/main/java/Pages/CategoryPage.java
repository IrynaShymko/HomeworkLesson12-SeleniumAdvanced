package Pages;

import Base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class CategoryPage extends BasePage {
    private WebDriver driver;
    private static Logger logger = LoggerFactory.getLogger("CategoryPage.class");

    public CategoryPage(WebDriver driver) {
        super(driver);
        logger.info("########## CategoryPage is created");
    }

    @FindBy(xpath = "//h1")
    private WebElement categoryTitle;

    @FindBy(xpath = "//p[contains(text(), 'Filter By')]")
    private WebElement categorySideBarMenu;

    @FindBy(xpath = "//div[@class='col-md-6 hidden-sm-down total-products']/p")
    private WebElement countConfirmationMessage;

    @FindBy(xpath = "//div[@itemprop='itemListElement']")
    private List<WebElement> foundedInCategoryProductsList;

    @FindBy(xpath = "//ul[@class='category-sub-menu']//a")
    private List<WebElement> subcategoryList;


    public String getCategoryTitle(){
        logger.info("<<<<<<<<<< Category title is: "+categoryTitle.getText());
       return categoryTitle.getText();
    }

    public Boolean isDisplayedCategorySideBarMenu (){
        if (categorySideBarMenu !=null){
            logger.info("<<<<<<<<<< Category side bar menu is displayed");
            return true;
        }
        else {return false;}
    }

    public Boolean accordingCountProductsWithConfirmationMessage(){
        if(countConfirmationMessage.getText().contains(String.valueOf(foundedInCategoryProductsList.size()))){
            logger.info("<<<<<<<<<< Count confirmationMessage is: "+countConfirmationMessage.getText() );
            logger.info("<<<<<<<<<< Count of founded products is: "+ foundedInCategoryProductsList.size());
            return true;
        }
        else {return false;}
    }

    public List<WebElement> getSubcategories(){
        return subcategoryList;
    }
}
