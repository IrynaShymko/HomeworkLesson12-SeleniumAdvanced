package helpers;

import Pages.*;
import PagesTest.TestBase;

public class Pages extends TestBase {

    public static CategoryPage categoryPage;
    public static ProductDetailsPage productDetailsPage;
    public static ModalCartPage modalCartPage;
    public static BasketPage basketPage;
    public static RegistrationPage registrationPage;
    public static LoginPage loginPage;
    public static OrderDataPage orderDataPage;
    public static TermsOfServiceModalPage termsOfServiceModalPage;
    public static OrderConfirmationPage orderConfirmationPage;
    public static AccountPage accountPage;
    public static OrderHistoryPage orderHistoryPage;
    public static OrderDetailsPage orderDetailsPage;


    public Pages() {
        categoryPage = new CategoryPage(driver);
        productDetailsPage = new ProductDetailsPage(driver);
        modalCartPage = new ModalCartPage(driver);
        basketPage = new BasketPage(driver);
        registrationPage = new RegistrationPage(driver);
        loginPage = new LoginPage(driver);
        orderDataPage = new OrderDataPage(driver);
        termsOfServiceModalPage = new TermsOfServiceModalPage(driver);
        orderConfirmationPage = new OrderConfirmationPage(driver);
        accountPage = new AccountPage(driver);
        orderHistoryPage = new OrderHistoryPage(driver);
        orderDetailsPage = new OrderDetailsPage(driver);
    }


}

