package PagesTest;

import Pages.SearchResultsPage;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class SearchResultsPageTest extends TestBase {
    private static Logger logger = LoggerFactory.getLogger("SearchResultsPageTest.class");

    public SearchResultsPageTest(){
        super(driver);
    }

    @Test
    public void shouldMatchProductNamesOnSearchResultsPage(){
        String productName = mainPage.getRandomProductNameFromMainPage();
        topPanelPage.searchProduct(productName);
        assertThat("Product names do not match", productName, equalTo(new SearchResultsPage(driver).getNameFoundedProduct()));
    }
}
