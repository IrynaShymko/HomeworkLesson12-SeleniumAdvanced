package PagesTest;

import Pages.CategoryPage;
import Pages.ModalCartPage;
import Pages.ProductDetailsPage;
import Helpers.ProductBox;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AddingProductsToBasketTest extends TestBase {
    private static int timesToRepeat = 3;
    private static Logger logger = LoggerFactory.getLogger("AddingProductsToBasketTest.class");
    private int maxQuantityOfProduct = 5;
    private int minQuantityOfProduct = 1;
    private CategoryPage categoryPage = new CategoryPage(driver);
    private ProductDetailsPage productDetailsPage= new ProductDetailsPage(driver);
    private ProductBox productBox = new ProductBox();
    private ModalCartPage modalCartPage =new ModalCartPage(driver);

    @Test
    public void  shouldSuccessfullyAddToShoppingCart() {
        for (int i=0; i<timesToRepeat; i++) {
            topPanelPage.chooseRandomCategory();
            categoryPage.chooseRandomProductInCategory();
            productDetailsPage.chooseQuantityOfProduct(minQuantityOfProduct, maxQuantityOfProduct);
            productDetailsPage.changeProductBoxContent(productBox);
            productDetailsPage.addProductToCart();
            assertTrue(modalCartPage.isProductInfoInCartTheSameInProductBox(productBox), "Products are different");
            assertTrue(modalCartPage.isCommonInfoInCartTheSameInProductBox(productBox), "Common Info is different");
            modalCartPage.clickContinueShoppingButton();
            assertTrue(topPanelPage.isQuantityOnCartIconOnTopPanelTheSameInBox(productBox), "QuantityOnCartIconOnTopPanel is not TheSameInBox");
        }

    }

}
