package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Class with methods to wait.
 *
 * @author Shakhov Yevhen.
 */

public abstract class AbstractWaits {
    final WebDriverWait wait;
    final WebDriver driver;

    /**
     * Class constructor.
     *
     * @param driver
     */
    AbstractWaits(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait( driver, 10 );
    }

    /**
     * Method for waiting for an item.
     *
     * @param by
     */
    public void waitElement(By by) {
        wait.until( ExpectedConditions.elementToBeClickable( by ) );
    }

    /**
     * Method for expecting an item to be visible.
     *
     * @param by
     */
    public void waitElementVisible(By by) {
        wait.until( ExpectedConditions.visibilityOfElementLocated( by ) );
    }

    /**
     * Method for page wait.
     *
     * @param url
     */
    public void waitURL(String url) {
        wait.until( ExpectedConditions.urlContains( url ) );
    }
}

