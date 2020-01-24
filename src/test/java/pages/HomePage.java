package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import properties.AbstractProperties;

import java.util.List;

/**
 * Class that describes the homepage
 *
 * @author Shakhov Yevhen.
 */

public class HomePage extends AbstractSettings {
    private final By currencyEUR = By.xpath( "//a[.='EUR €']" );
    private final By currencyUAH = By.xpath( "//a[.='UAH ₴']" );
    private final By currencyUSD = By.xpath( "//a[.='USD $']" );
    private final By currencyButton = By.xpath( "//div[@class='currency-selector dropdown js-dropdown']//a[@data-target='#']" );
    private final By search = By.xpath( "//form[@method='get']/input[2]" );
    private final By commodityCurrencies = By.xpath( "//div//span[@itemprop='price']" );


    /**
     * Сhecking the coincidence of the selected price on the site with the price of goods
     *
     * @param currency
     * @return
     */
    public boolean currencyCheckOnThePage(Currency currency) {
        waitElement( commodityCurrencies );
        List<WebElement> currencies = driver.findElements( commodityCurrencies );
        AbstractProperties.log( "Verifying that products contain the correct currency type" );
        switch (currency) {
            case EUR:
                for (WebElement curr : currencies) {
                    if (curr.getText().contains( "€" )) {
                        AbstractProperties.log( "Commodity currency - " + curr.getText() + " matches the selected currency on the page" );
                    } else {
                        return false;
                    }
                }
                return true;
            case UAH:
                for (WebElement curr : currencies) {
                    if (curr.getText().contains( "₴" )) {
                        AbstractProperties.log( "Commodity currency - " + curr.getText() + " matches the selected currency on the page" );
                    } else {
                        return false;
                    }
                }
                return true;
            case USD:
                for (WebElement curr : currencies) {
                    if (curr.getText().contains( "$" )) {
                        AbstractProperties.log( "Commodity currency - " + curr.getText() + " matches the selected currency on the page" );
                    } else {
                        return false;
                    }
                }
                return true;
            default:
                throw new IllegalArgumentException( "You entered the wrong currency" );
        }
    }

    /**
     * Currency selection
     *
     * @param currency
     */
    public void currencySelection(Currency currency) {
        waitElement( currencyButton );
        driver.findElement( currencyButton ).click();
        switch (currency) {
            case EUR:
                waitElement( currencyEUR );
                driver.findElement( currencyEUR ).click();
                break;
            case UAH:
                waitElement( currencyUAH );
                driver.findElement( currencyUAH ).click();
                break;
            case USD:
                waitElement( currencyUSD );
                driver.findElement( currencyUSD ).click();
                break;
        }
    }

    /**
     * Product search
     *
     * @param product
     * @return
     */
    public SortingPage productSearch(String product) {
        waitElement( search );
        WebElement searchField = driver.findElement( search );
        searchField.sendKeys( product );
        searchField.sendKeys( Keys.ENTER );
        return new SortingPage();
    }


}
