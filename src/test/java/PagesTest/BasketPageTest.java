package PagesTest;

import Helpers.ProductBox;
import helpers.Pages;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.*;

public class BasketPageTest extends Pages {
    private static Logger logger = LoggerFactory.getLogger("BasketPageTest.class");
    private static int howManyKindsOfProductsToAdd = 5;
    private int maxQuantityOfProduct = 5;
    private int minQuantityOfProduct = 1;
    private String newQuantityOfFirstProduct = "5";
    private ProductBox productBox = new ProductBox();

    @Test
    public void shouldSuccessfullyAddToShoppingCart() {
        for (int i = 0; i < howManyKindsOfProductsToAdd; i++) {
            topPanelPage.chooseRandomCategory();
            categoryPage.chooseRandomProductInCategory();
            productDetailsPage.chooseQuantityOfProduct(minQuantityOfProduct, maxQuantityOfProduct)
                    .changeProductBoxContent(productBox)
                    .addProductToCart();
            modalCartPage.clickContinueShoppingButton();
        }
        topPanelPage.clickCartIcon();
        assertAll("Error in basket",
                () -> assertEquals(productBox.getProducts().size(), basketPage.getNumberProductsInCart(), "Basket: Different size of products in ProductBox and in Cart"),
                () -> assertTrue(basketPage.isProductDataInCartTheSameInProductBox(productBox), "Data of products in Basket and in ProductBox Is different"),
                () -> assertTrue(basketPage.isTotalCostTheSameInCartThatInProductBox(productBox), "TotalCost in Basket and in ProductBox Is different"));

        basketPage.changeQuantityOfFirstProduct(productBox, newQuantityOfFirstProduct);
        assertAll("Error in basket",
                () -> assertTrue(basketPage.isTotalCostTheSameInCartThatInProductBox(productBox), "TotalCost in Basket and in ProductBox Is different after change"),
                () -> assertTrue(basketPage.increasedQuantityAfterClickingArrowUpButton(productBox), "Quantity did not increased after clicking ArrowUp button"),
                () -> assertTrue(basketPage.isTotalCostTheSameInCartThatInProductBox(productBox), "TotalCost in Basket and in ProductBox Is different"),
                () -> assertTrue(basketPage.decreasedQuantityAfterClickingArrowDownButton(productBox), "Quantity did not decreased after clicking ArrowDown button"),
                () -> assertTrue(basketPage.isTotalCostTheSameInCartThatInProductBox(productBox), "TotalCost in Basket and in ProductBox Is different"),
                () -> assertTrue(basketPage.isCorrectTotalCostAfterRemovingProductsOneByOne(productBox), "TotalCost in Basket and in ProductBox Is different"));
    }
}
