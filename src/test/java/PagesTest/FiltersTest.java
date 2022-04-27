package PagesTest;

import Pages.CategoryPage;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FiltersTest extends TestBase {
    private CategoryPage categoryPage = new CategoryPage(driver);
    private int minValue1 = 9;
    private int maxValue1 = 10;
    private int minValue2 = 11;
    private int maxValue2 = 29;
    private static Logger logger = LoggerFactory.getLogger("FiltersTest.class");

    @Test
    public void shouldShowProductsAccordingToPriceFilters() {
        topPanelPage.navigateToArtCategory();
        categoryPage.filterByPrice(minValue1, maxValue1);
        assertTrue(categoryPage.isPriceInChosenBounds(minValue1, maxValue1), "Filter by prices is incorrect");
        categoryPage.filterByPrice(minValue2, maxValue2);
        assertTrue(categoryPage.isPriceInChosenBounds(minValue2, maxValue2), "Filter by prices is incorrect");
    }
}
