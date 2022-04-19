package PagesTest;

import Pages.CategoryPage;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class CategoryPageTest extends TestBase {
    private Boolean[] successResultArray = {true, true, true};
    private List<String> categoryNamesOnTopPanel = new ArrayList<>();
    private  List<String> categoryTitlesOnPage = new ArrayList<>();
    private  List<Boolean> confirmationOfDisplayingSideBar = new ArrayList<>();
    private  List<Boolean> confirmationCountOfProducts = new ArrayList<>();
    private static Logger logger = LoggerFactory.getLogger("CategoryPageTest.class");

    public CategoryPageTest() {
        super(driver);
    }

    @Test
    public void shouldMatchInfoInCategories() {
        List<Boolean> successResult = Arrays.asList(successResultArray);
        List<WebElement> categories = topPanelPage.getCategoriesList();
        for (int i = 0; i < categories.size(); i++) {
            categoryNamesOnTopPanel.add(categories.get(i).getText());
            categories.get(i).click();
            CategoryPage categoryPage = new CategoryPage(driver);
            categoryTitlesOnPage.add(categoryPage.getCategoryTitle());
            confirmationOfDisplayingSideBar.add(categoryPage.isDisplayedCategorySideBarMenu());
            confirmationCountOfProducts.add(categoryPage.accordingCountProductsWithConfirmationMessage());
        }
        logger.info("<<<<<<<<<< List of category names on Top Panel is: " + categoryNamesOnTopPanel);
        logger.info("<<<<<<<<<< List of category titles is: " + categoryTitlesOnPage);
        logger.info("<<<<<<<<<< List of side bar confirmations is: " + confirmationOfDisplayingSideBar);
        logger.info("<<<<<<<<<< List of matches count of products confirmations: " + confirmationCountOfProducts);

        assertAll("Error in categories",
                () -> assertEquals(categoryNamesOnTopPanel, categoryTitlesOnPage),
                () -> assertEquals(successResult, confirmationOfDisplayingSideBar),
                () -> assertEquals(successResult, confirmationCountOfProducts));
    }
}
