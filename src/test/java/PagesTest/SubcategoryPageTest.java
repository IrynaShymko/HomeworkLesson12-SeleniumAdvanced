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

public class SubcategoryPageTest extends TestBase {
    private Boolean[] successResultArray = {true, true, true, true};
    private List<String> subcategoryNamesOnTopPanel = new ArrayList<>();
    private List<String> subcategoryTitlesOnPage = new ArrayList<>();
    private List<Boolean> confirmationOfDisplayingSideBar = new ArrayList<>();
    private List<Boolean> confirmationCountOfProducts = new ArrayList<>();
    private static Logger logger = LoggerFactory.getLogger("CategoryPageTest.class");

    public SubcategoryPageTest() {
        super(driver);
    }

    @Test
    public void shouldMatchInfoInCategories() {
        List<Boolean> successResult = Arrays.asList(successResultArray);
        List<WebElement> categories = topPanelPage.getCategoriesList();
        for (int i = 0; i < categories.size() - 1; i++) {
            categories.get(i).click();
            CategoryPage categoryPage = new CategoryPage(driver);
            List<WebElement> subcategories = categoryPage.getSubcategories();
            for (int j = 0; j < subcategories.size(); j++) {
                subcategoryNamesOnTopPanel.add(subcategories.get(j).getText().toUpperCase());
                subcategories.get(j).click();
                subcategoryTitlesOnPage.add(categoryPage.getCategoryTitle());
                confirmationOfDisplayingSideBar.add(categoryPage.isDisplayedCategorySideBarMenu());
                confirmationCountOfProducts.add(categoryPage.accordingCountProductsWithConfirmationMessage());
                categories.get(i).click();
            }
        }
        logger.info("<<<<<<<<<< List of subcategory names on Panel is: " + subcategoryNamesOnTopPanel);
        logger.info("<<<<<<<<<< List of subcategory titles is: " + subcategoryTitlesOnPage);
        logger.info("<<<<<<<<<< List of side bar confirmations is: " + confirmationOfDisplayingSideBar);
        logger.info("<<<<<<<<<< List of matches count of products confirmations: " + confirmationCountOfProducts);

        assertAll("Error in subcategories ",
                () -> assertEquals(subcategoryNamesOnTopPanel, subcategoryTitlesOnPage),
                () -> assertEquals(successResult, confirmationOfDisplayingSideBar),
                () -> assertEquals(successResult, confirmationCountOfProducts));
    }
}
