package PagesTest;

import Pages.CategoryPage;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FiltersTest extends TestBase{
    private static Logger logger = LoggerFactory.getLogger("FiltersTest.class");

    @Test
    public void shouldShowProductsAccordingToPriceFilters() {
        topPanelPage.navigateToArtCategory();
        CategoryPage categoryPage = new CategoryPage(driver);
        categoryPage.navigateToPriceFilter();
        categoryPage.moveTopSliderHandler(Integer.parseInt(System.getProperty("maxValue1")));
        categoryPage.moveBottomSliderHandler(Integer.parseInt(System.getProperty("minValue1")));

        assertTrue(categoryPage.isPriceInChosenBounds(Integer.parseInt(System.getProperty("minValue1")), Integer.parseInt(System.getProperty("maxValue1"))),
                "Filter by prices is incorrect");

//        tut dopisac metod cleanFilter

//        categoryPage.moveTopSliderHandler(Integer.parseInt(System.getProperty("maxValue2")));
//        categoryPage.moveBottomSliderHandler(Integer.parseInt(System.getProperty("minValue2")));
//        assertTrue(categoryPage.isPriceInChosenBounds(Integer.parseInt(System.getProperty("minValue2")), Integer.parseInt(System.getProperty("maxValue2"))));


    }
}
