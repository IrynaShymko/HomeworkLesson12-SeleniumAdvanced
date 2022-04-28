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


public class CategoryPageTest extends TestBase {
    private CategoryPage categoryPage = new CategoryPage(driver);
    private static Logger logger = LoggerFactory.getLogger("CategoryPageTest.class");


    @Test
    public void shouldMatchInfoInCategories() {
        List<WebElement> categories = topPanelPage.getCategoriesList();
        for (int i = 0; i < categories.size(); i++) {
            String categoryName = categories.get(i).getText();
            categories.get(i).click();
            logger.info("<<<<<<<<<< Category Name in top panel is: " + categoryName);
            assertAll("Error in categories",
                    () -> assertEquals(categoryName, categoryPage.getCategoryTitle(), "Different titles on top panel and categoryPage"),
                    () -> assertTrue(categoryPage.isDisplayedCategorySideBarMenu(), "Side bar is not displayed"),
                    () -> assertTrue(categoryPage.accordingCountProductsWithConfirmationMessage(), "Count of products is incorrect"));
        }
    }
}
