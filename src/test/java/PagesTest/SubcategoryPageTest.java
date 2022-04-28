package PagesTest;

import Pages.CategoryPage;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SubcategoryPageTest extends TestBase {
    private CategoryPage categoryPage = new CategoryPage(driver);
    private static Logger logger = LoggerFactory.getLogger("CategoryPageTest.class");


    @Test
    public void shouldMatchInfoInCategories() {
        List<WebElement> categories = topPanelPage.getCategoriesList();
        List<WebElement> subcategories = categoryPage.getSubcategories();

        for (int i = 0; i < categories.size() - 1; i++) {
            categories.get(i).click();
            for (int j = 0; j < subcategories.size(); j++) {
                String subcategoryName = subcategories.get(j).getText().toUpperCase();
                subcategories.get(j).click();
                logger.info("<<<<<<<<<< Subcategory Name in top panel is: " + subcategoryName);
                assertAll("Error in categories",
                        () -> assertEquals(subcategoryName, categoryPage.getCategoryTitle(), "Different titles on top panel and categoryPage"),
                        () -> assertTrue(categoryPage.isDisplayedCategorySideBarMenu(), "Side bar is not displayed"),
                        () -> assertTrue(categoryPage.accordingCountProductsWithConfirmationMessage(), "Count of products is incorrect"));
                categories.get(i).click();
            }
        }
    }
}
