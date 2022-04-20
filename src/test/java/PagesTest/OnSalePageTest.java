package PagesTest;

import Pages.OnSalePage;
import Pages.ProductDetailsPage;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.*;

public class OnSalePageTest extends TestBase{
    private static Logger logger = LoggerFactory.getLogger("OnSalePageTest.class");

    @Test
    public void shouldCheckOnSalePage() {
        footerPage.clickOnLinkPricesDrop();
        OnSalePage onSalePage= new OnSalePage(driver);

        assertAll("Error in onSalePage",
                () -> assertEquals(onSalePage.getTitleOnSalePage(), System.getProperty("titleOnSalePage")),
                () -> assertTrue(onSalePage.isDisplayedDiscountLabelOnProduct()),
                () -> assertTrue(onSalePage.isDisplayedTwoPricesOnProduct()),
                () -> assertTrue(onSalePage.isDiscountedPriceLowerThanRegularOnDiscountSize()));
       onSalePage.clickRandomValueFromListOfProductsOnSale();
       ProductDetailsPage productDetailsPage =new ProductDetailsPage(driver);

                assertAll("Error in productDetailsPage",
                () -> assertTrue(productDetailsPage.hasProductPageDiscountLabel()),
                () -> assertTrue(productDetailsPage.isDisplayedTwoPricesOnProduct()),
                () -> assertTrue(productDetailsPage.OnProductDetailsIsDiscountedPriceLowerThanRegularOnDiscountSize()));
    }
}
