package PagesTest;

import Pages.CategoryPage;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FiltersTest extends TestBase {
    private CategoryPage categoryPage = new CategoryPage(driver);
    private int minValue1 = Integer.parseInt(System.getProperty("minValue1"));
    private int maxValue1 = Integer.parseInt(System.getProperty("maxValue1"));
    private int minValue2 = Integer.parseInt(System.getProperty("minValue2"));
    private int maxValue2 = Integer.parseInt(System.getProperty("maxValue2"));
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
