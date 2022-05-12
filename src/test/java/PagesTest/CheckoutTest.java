package PagesTest;

import Helpers.OrderDataInfo;
import Helpers.ProductBox;
import Helpers.User;
import Helpers.UserFactory;
import Pages.*;
import helpers.Pages;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CheckoutTest extends Pages {
    private static Logger logger = LoggerFactory.getLogger("CheckoutTest.class");
    int firstNameLength = 6;
    int lastNameLength = 9;
    int passwordLength = 6;
    private static int howManyKindsOfProductsToAdd = 5;
    private int minQuantityOfProduct = 1;
    private int maxQuantityOfProduct = 3;
    private OrderDataInfo orderDataInfo = new OrderDataInfo();
    private ProductBox productBox = new ProductBox();

    @Test
    public void shouldAddProductsAndCheckOutSuccessfully() {
        User user = UserFactory.getRandomUser(firstNameLength, lastNameLength, passwordLength);
        topPanelPage.clickSignInButton();
        loginPage.clickCreateAccountLink();
        registrationPage
                .fillRegistrationForm(user)
                .submitRegistrationForm();
        for (int i = 0; i < howManyKindsOfProductsToAdd; i++) {
            topPanelPage.chooseRandomCategory();
            categoryPage.chooseRandomProductInCategory();
            productDetailsPage
                    .chooseQuantityOfProduct(minQuantityOfProduct, maxQuantityOfProduct)
                    .changeProductBoxContent(productBox)
                    .addProductToCart();
            if (i == howManyKindsOfProductsToAdd - 1) {
                modalCartPage.clickProceedToCheckoutButton();
            } else {
                modalCartPage.clickContinueShoppingButton();
            }
        }
        basketPage.clickOnProceedToCheckoutButtonInCart();
        orderDataPage
                .fillAddressesSection()
                .fillDeliverySection()
                .selectPaymentMethod()
                .openTermsOfServiceLink();
        boolean isContentOfTermsOfServiceNonEmpty = termsOfServiceModalPage.isContentOfTermsOfServiceNonEmpty();
        termsOfServiceModalPage.closeModalTermsOfService();
        orderDataPage
                .acceptTermOfService()
                .clickPlaceOrderButton();
        assertAll("Error in order confirmation page",
                () -> assertTrue(isContentOfTermsOfServiceNonEmpty, "Content of Terms of service is empty"),
                () -> assertTrue(orderConfirmationPage.isProductDataInOrderConfirmationTheSameInProductBox(productBox), "Products in Order confirmation and in Box are different"),
                () -> assertTrue(orderConfirmationPage.isPaymentMethodTheSameWasSelected(), "Payment method in Order confirmation and selected are different"),
                () -> assertTrue(orderConfirmationPage.isShippingMethodTheSameWasSelected(), "Shipping method in Order confirmation and selected are different"));

        orderConfirmationPage.saveOrderReferenceNumber(orderDataInfo);
        topPanelPage.navigateToAccountPage();
        accountPage.clickOnOrderHistoryLink();
        boolean isCommonOrderInfoTheSameInProductBox = orderHistoryPage.isCommonOrderInfoTheSameInProductBox(orderDataInfo, productBox);
        orderHistoryPage.clickOnDetailsLink(orderDataInfo);
        assertAll("Error in order details page",
                () -> assertTrue(isCommonOrderInfoTheSameInProductBox, "Common order info IS NOT the same in product box"),
                () -> assertTrue(orderDetailsPage.isProductDataInOrderDetailsTheSameInProductBox(productBox), "Products in Order details and in Box are different"),
                () -> assertTrue(orderDetailsPage.isDeliveryAddressOnDetailsCorrect(), "Delivery address is incorrect on the Order details"),
                () -> assertTrue(orderDetailsPage.isInvoiceAddressOnDetailsCorrect(), "Invoice address is incorrect on the Order details"));
    }
}
