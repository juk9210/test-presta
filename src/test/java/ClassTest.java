import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.Currency;
import pages.HomePage;
import pages.SortingPage;
import properties.AbstractProperties;

/**
 * Class for writing tests
 *
 * @author Shakhov Yevhen.
 */
public class ClassTest extends AbstractProperties {
    private static EventFiringWebDriver eDriver;
    private String HomeUrl = "http://prestashop-automation.qatestlab.com.ua/ru/";

    /**
     * Installations before running tests
     */
    @BeforeTest
    public void setUp() {
        eDriver = AbstractProperties.getConfigures();
        eDriver.get( HomeUrl );
    }


    @Test
    public void test() {
        HomePage homePage = new HomePage( eDriver );
        homePage.currencySelection( Currency.USD );
        Assert.assertTrue( homePage.currencyCheckOnThePage( Currency.USD ) );

        homePage.currencySelection( Currency.EUR );
        Assert.assertTrue( homePage.currencyCheckOnThePage( Currency.EUR ) );

        homePage.currencySelection( Currency.UAH );
        Assert.assertTrue( homePage.currencyCheckOnThePage( Currency.UAH ) );

        homePage.currencySelection( Currency.USD );

        homePage.productSearch( "dress" );
        SortingPage sortingPage = new SortingPage( eDriver );
        Assert.assertEquals( sortingPage.getResultOnPage(), "Товаров: " + sortingPage.getAllProducts() + "." );
        Assert.assertTrue( sortingPage.currencyCheck( Currency.USD ) );
        sortingPage.sortingSelection();
        Assert.assertTrue( sortingPage.checkingCorrectSorting() );
        Assert.assertTrue( sortingPage.checkDiscountProducts() );
        Assert.assertTrue( sortingPage.checkPricesBeforeAndAfterDiscount() );
    }

    /**
     * Installation after running tests
     */
    @AfterTest
    public void tearDown() {
        if (eDriver != null) {
            eDriver.quit();
        }
        writeLogs();
    }
}
