package PagesTest;

import Helpers.ProductBox;
import helpers.Pages;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AddingProductsToBasketTest extends Pages {
    private static int timesToRepeat = 3;
    private static Logger logger = LoggerFactory.getLogger("AddingProductsToBasketTest.class");
    private int maxQuantityOfProduct = 5;
    private int minQuantityOfProduct = 1;
    private ProductBox productBox = new ProductBox();

    @Test
    public void shouldSuccessfullyAddToShoppingCart() {
        for (int i = 0; i < timesToRepeat; i++) {
            topPanelPage.chooseRandomCategory();
            categoryPage.chooseRandomProductInCategory();
            productDetailsPage.chooseQuantityOfProduct(minQuantityOfProduct, maxQuantityOfProduct)
                    .changeProductBoxContent(productBox)
                    .addProductToCart();
            assertTrue(modalCartPage.isProductInfoInCartTheSameInProductBox(productBox), "Products are different");
            assertTrue(modalCartPage.isCommonInfoInCartTheSameInProductBox(productBox), "Common Info is different");
            modalCartPage.clickContinueShoppingButton();
            assertTrue(topPanelPage.isQuantityOnCartIconOnTopPanelTheSameInBox(productBox), "QuantityOnCartIconOnTopPanel is not TheSameInBox");
        }

    }

}
