package pages;

import com.google.common.collect.Ordering;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import properties.AbstractProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that describes the page after the search
 *
 * @author Shakhov Yevhen.
 */

public class SortingPage extends HomePage {
    private final By resultOfProducts = By.xpath( "//div[@class='col-md-6 hidden-sm-down total-products']" );
    private final By basicPrices = By.xpath( "//div[@class='product-price-and-shipping']/span[1]" );
    private final By sortButton = By.xpath( "//a[@class='select-title']" );
    private final By sortText = By.linkText( "Цене: от высокой к низкой" );
    private final By productsWithDiscount = By.xpath( "//li[@class='discount']" );
    private final By discountPrices = By.xpath( "//div[@class='product-price-and-shipping']/span[3]" );
    private final By discount = By.xpath( "//span[@class='discount-percentage']" );
    private final By basicPricesOfDiscountProducts = By.xpath( "//span[@class='regular-price']" );
    private final String sortingURL = "http://prestashop-automation.qatestlab.com.ua/ru/search?controller=search&order=product.price.desc&s=dress";


    /**
     * Get the inscription of the quantity of goods
     *
     * @return
     */
    public String getResultOnPage() {
        waitElementVisible( resultOfProducts );
        return driver.findElement( resultOfProducts ).getText();
    }

    /**
     * Get the number of products found
     *
     * @return
     */
    public int getAllProducts() {
        waitElement( basicPrices );
        return driver.findElements( basicPrices ).size();
    }

    /**
     * Checking currency
     *
     * @param currency
     * @return
     */
    public boolean currencyCheck(Currency currency) {
        waitElement( basicPrices );
        List<WebElement> prices = driver.findElements( basicPrices );
        AbstractProperties.log( "Verifying that all the products contain $ currency symbol" );
        for (WebElement pr : prices) {
            if (!pr.getText().contains( "$" )) {
                AbstractProperties.log( pr.getText() + " not in currency $" );
                return false;
            }
        }
        AbstractProperties.log( "All products in currency $" );
        return true;
    }

    /**
     * Sorting goods
     */
    public void sortingSelection() {
        waitElement( sortButton );
        driver.findElement( sortButton ).click();
        waitElement( sortText );
        driver.findElement( sortText ).click();
    }

    /**
     * Checking the correct sorting
     *
     * @return
     * @throws InterruptedException
     */
    public boolean checkingCorrectSorting() {
        waitURL( sortingURL );
        List<WebElement> prices = driver.findElements( basicPrices );
        List<String> values = new ArrayList<String>();
        AbstractProperties.log( "Verifying sorting method \"prices: from high to low\"" );
        for (WebElement element : prices) {
            values.add( element.getText() );
        }
        boolean isSorted = Ordering.natural().reverse().isOrdered( values );
        if (!isSorted) {
            AbstractProperties.log( "Items are not sorted correctly" );
            return false;
        }
        AbstractProperties.log( "Products are sorted correctly." );
        return true;
    }

    /**
     * Check that discounted goods are indicated - old price, discount, discount price
     *
     * @return
     */
    public boolean checkDiscountProducts() {
        List<WebElement> productsWithDiscounts = driver.findElements( productsWithDiscount );
        List<WebElement> discountsPrices = driver.findElements( discountPrices );
        List<WebElement> discounts = driver.findElements( discount );
        List<WebElement> basicPricesOfDiscountsProducts = driver.findElements( basicPricesOfDiscountProducts );
        AbstractProperties.log( "Verifying each product on-sale to has a label with discount, actual and regular price" );
        AbstractProperties.log( "Found: " + productsWithDiscounts.size() + " products with discount" );
        AbstractProperties.log( "Found: " + discounts.size() + " product signs of discount" );
        AbstractProperties.log( "Found: " + basicPricesOfDiscountsProducts.size() + " regular prices of discount products" );
        AbstractProperties.log( "Found: " + discountsPrices.size() + " actual prices of discount products" );
        for (WebElement element : discounts) {
            if (element.getText().contains( "%" )) {
                AbstractProperties.log( "Element: " + element.getText() + " contains discount!" );
            } else {
                AbstractProperties.log( "Element: " + element.getText() + " doesn't contain discount!" );
                return false;
            }
        }
        if (productsWithDiscounts.size() == discountsPrices.size() && productsWithDiscounts.size() == discounts.size() && productsWithDiscounts.size() == basicPricesOfDiscountsProducts.size()) {
            AbstractProperties.log( "Quantity of products with discount: " + productsWithDiscounts.size() + " equals to quantity of discount signs: " + discountsPrices.size() );
            AbstractProperties.log( "Quantity of products with discount: " + productsWithDiscounts.size() + " equals to quantity of regular prices: " + discounts.size() );
            AbstractProperties.log( "Quantity of products with discount: " + productsWithDiscounts.size() + " equals to quantity of actual prices: " + basicPricesOfDiscountsProducts.size() );
            return true;
        } else {
            AbstractProperties.log( "Quantity of products with discount doesn't to one of parameters" );
            return false;
        }
    }
}
