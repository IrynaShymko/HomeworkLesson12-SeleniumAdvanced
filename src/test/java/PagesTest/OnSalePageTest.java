package PagesTest;

import Pages.OnSalePage;
import Pages.ProductDetailsPage;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.*;

public class OnSalePageTest extends TestBase {
    private static Logger logger = LoggerFactory.getLogger("OnSalePageTest.class");

    @Test
    public void shouldCheckOnSalePage() {
        footerPage.clickOnLinkPricesDrop();
        OnSalePage onSalePage = new OnSalePage(driver);

        assertAll("Error in onSalePage",
                () -> assertEquals(onSalePage.getTitleOnSalePage(), System.getProperty("titleOnSalePage"), "Title on OnSale Page and title expected are different"),
                () -> assertTrue(onSalePage.isDisplayedDiscountLabelOnProduct(), "OnSalePage: Discount Label On Product is not displayed"),
                () -> assertTrue(onSalePage.isDisplayedTwoPricesOnProduct(), "OnSalePage: Regular or discounted prices are not displayed"),
                () -> assertTrue(onSalePage.isDiscountedPriceLowerThanRegularOnDiscountSize(), "OnSalePage: Difference between regular and discounted price is incorrect"));
        onSalePage.clickRandomValueFromListOfProductsOnSale();
        ProductDetailsPage productDetailsPage = new ProductDetailsPage(driver);

        assertAll("Error in productDetailsPage",
                () -> assertTrue(productDetailsPage.hasProductPageDiscountLabel(), "productDetailsPage: Discount Label On Product is not displayed"),
                () -> assertTrue(productDetailsPage.isDisplayedTwoPricesOnProduct(), "productDetailsPage: Regular or discounted prices are not displayed"),
                () -> assertTrue(productDetailsPage.OnProductDetailsIsDiscountedPriceLowerThanRegularOnDiscountSize(), "productDetailsPage: Difference between regular and discounted price is incorrect"));
    }
}
