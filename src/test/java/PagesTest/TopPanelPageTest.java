package PagesTest;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

public class TopPanelPageTest extends TestBase {
    private static Logger logger = LoggerFactory.getLogger("TopPanelPageTest.class");

    public TopPanelPageTest(){
        super(driver);
    }

    @Test
    public void shouldMatchProductNamesOnDropdown() {
        String productName = mainPage.getRandomProductNameFromMainPage();
        logger.info("<<<<<<<<<< Chosen Product Name is: "+productName);
        topPanelPage.fillSearchField(productName);
        assertThat("Product names do not match", topPanelPage.getProposedProductNameFromDropdown(), containsString(productName));
    }
}
